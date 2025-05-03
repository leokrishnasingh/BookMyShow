package com.example.bookmyshow.Repository;

import com.example.bookmyshow.models.ShowSeat;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowseatRepository extends JpaRepository<ShowSeat , Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("Select ss from ShowSeat ss where ss.id IN :Ids")
    List<ShowSeat> findAllShowSeatAndLock(List<Long> Ids);
}
