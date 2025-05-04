package com.example.bookmyshow.configs;

import com.example.bookmyshow.Repository.ScreenRepository;
import com.example.bookmyshow.Repository.TheaterRepository;
import com.example.bookmyshow.models.Screen;
import com.example.bookmyshow.models.Theater;
import com.example.bookmyshow.models.enums.Feature;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Configuration
public class RunnerConfig implements ApplicationRunner {

    ScreenRepository screenRepository;
    TheaterRepository theaterRepository;
    RunnerConfig(ScreenRepository screenRepository,
                 TheaterRepository theaterRepository) {
        this.screenRepository = screenRepository;
        this.theaterRepository = theaterRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Running Config");

        Screen screen = new Screen();

        screen.setCapacity(400);
        screen.setName("A4");

        Optional<Theater> optionalTheater = theaterRepository.findById(1L);
        screen.setTheater(optionalTheater.get());
        screen.setFeatures(List.of(Feature.DOLBY , Feature.IMAX,Feature.THREE_D));
        screenRepository.save(screen);
    }
}
