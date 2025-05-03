package com.example.bookmyshow.services;

import com.example.bookmyshow.Adapter.DTO.ThirdPartyPaymentStatus;
import com.example.bookmyshow.Adapter.DTO.paymentRequestDTO;
import com.example.bookmyshow.Adapter.DTO.paymentResponseDTO;
import com.example.bookmyshow.Adapter.PaymentAdapter;
import com.example.bookmyshow.Adapter.ThirdPartyPayment;
import com.example.bookmyshow.Repository.BookingRepository;
import com.example.bookmyshow.Repository.ShowRepository;
import com.example.bookmyshow.Repository.ShowseatRepository;
import com.example.bookmyshow.Repository.UserRepository;
import com.example.bookmyshow.exceptions.ShowNotAvailable;
import com.example.bookmyshow.exceptions.UserNotFoundException;
import com.example.bookmyshow.models.Booking;
import com.example.bookmyshow.models.Show;
import com.example.bookmyshow.models.ShowSeat;
import com.example.bookmyshow.models.User;
import com.example.bookmyshow.models.enums.BookingStatus;
import com.example.bookmyshow.models.enums.ShowSeatStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;


import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService{

    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;
    private final ShowRepository showRepository;
    private final ShowseatRepository showseatRepository;
    private final PriceCalculatorService priceCalculatorService;

    BookingServiceImpl(UserRepository userRepository, BookingRepository bookingRepository,
                       ShowRepository showRepository, ShowseatRepository showseatRepository,
                       PriceCalculatorService priceCalculatorService) {
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
        this.showRepository = showRepository;
        this.showseatRepository = showseatRepository;
        this.priceCalculatorService = priceCalculatorService;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public Booking bookTicket(Long showId, List<Long> showSeatIds, Long userId)
            throws UserNotFoundException, ShowNotAvailable {

        Optional<User> optionalUser = userRepository.findById(userId);
        if( optionalUser.isEmpty() ){
            throw new UserNotFoundException("User is not in the Database");
        }
        User user = optionalUser.get();

        Optional<Show> optionalShow = showRepository.findById(showId);
        if(optionalShow.isEmpty()){
            throw new ShowNotAvailable("Show is not available");
        }
        Show show = optionalShow.get();

        List<ShowSeat> showSeats = showseatRepository.findAllById(showSeatIds);
        if(showSeats.size() != showSeatIds.size()) throw new ShowNotAvailable("Show seats doesn't exist");

        for(ShowSeat showSeat : showSeats){
            if(!showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)){
                throw new ShowNotAvailable("Show seats already booked");
            }
        }

        /*
            check first if all the seats are available
            we need to block the seats that we are blocking
        */

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setBookingStatus(BookingStatus.PENDING);

        // now block the seats and start a new payment
        for(ShowSeat showSeat : showSeats) {
            showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
        }
        showseatRepository.saveAll(showSeats);

        // calculate the total amount
        Long amount = priceCalculatorService.calculatePrice(showSeats);

        booking.setAmount(amount);

        paymentRequestDTO requestDTO = new paymentRequestDTO();
        requestDTO.setAmount(amount);
        PaymentAdapter thirdPartyGateway = new ThirdPartyPayment();
        paymentResponseDTO responseDTO =  thirdPartyGateway.initiatePayment(requestDTO);

        if(responseDTO.getPaymentStatus() == ThirdPartyPaymentStatus.SUCCESS){
            //book all seats
            String seatIds = "";
            for(ShowSeat showSeat : showSeats) {
                showSeat.setShowSeatStatus(ShowSeatStatus.BOOKED);
                seatIds += seatIds + showSeat.getId()+" ";
            }
            showseatRepository.saveAll(showSeats);

            booking.setBookingStatus(BookingStatus.CONFIRMED);
            booking.setBookingNumber("show : "+ showSeats.getFirst().getShow().getScreen().getName() + "seatIds " + seatIds);
        }
        else{
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            booking.setBookingStatus(BookingStatus.CANCELLED);
        }

        return booking;
    }
}
