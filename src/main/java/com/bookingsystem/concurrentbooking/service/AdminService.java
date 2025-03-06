package com.bookingsystem.concurrentbooking.service;

import com.bookingsystem.concurrentbooking.dto.*;
import com.bookingsystem.concurrentbooking.entity.*;
import com.bookingsystem.concurrentbooking.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final CityRepository cityRepository;
    private final CinemaHallRepository cinemaHallRepository;
    private final MovieRepository movieRepository;
    private final ShowRepository showRepository;
    private final SeatRepository seatRepository;

    public CinemaHall addCinemaHall(CinemaHallRequest cinemaHallRequest) {
        City city = cityRepository.findById(cinemaHallRequest.getCityId()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"City not found"));

        CinemaHall cinemaHall = new CinemaHall();
        List<CinemaHall> cityCinemaHall = city.getCinemaHalls();
        cityCinemaHall.add(cinemaHall);
        cinemaHall.setCity(city);
        cinemaHall.setHallName(cinemaHallRequest.getHallName());
        cinemaHall.setTotalSeats(cinemaHallRequest.getTotalSeats());
        city.setCinemaHalls(cityCinemaHall);
        return cinemaHallRepository.save(cinemaHall);
    }

    public City addCity(CityRequest cityRequest) {
        String cityName = cityRequest.getCityName();
        City city = new City();
        city.setCityName(cityName);
        return cityRepository.save(city);
    }

    public Movie addMovie(MovieRequest movieRequest) {
        Movie movie = new Movie();
        movie.setTitle(movieRequest.getTitle());
        movie.setGenre(movieRequest.getGenre());
        movie.setDuration(movieRequest.getDuration());
        movie.setLanguage(movieRequest.getLanguage());

        return movieRepository.save(movie);
    }

    public Show addShow(ShowRequest showRequest) {
        Movie movie = movieRepository.findById(showRequest.getMovieId()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Movie not found"));

        CinemaHall cinemaHall = cinemaHallRepository.findById(showRequest.getHallId()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Hall not found"));

        Show show = new Show();
        show.setMovie(movie);
        show.setCinemaHall(cinemaHall);
        show.setStartTime(showRequest.getStartTime().toString());
        show.setEndTime(showRequest.getEndTime().toString());
        show.setPricePerSeat(showRequest.getPrice());

        return showRepository.save(show);
    }
    public Seat addSeat(SeatRequest seatRequest) {

        CinemaHall cinemaHall = cinemaHallRepository.findById(seatRequest.getHallId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Hall not found"));

        Seat seat = new Seat();
        seat.setCinemaHall(cinemaHall);
        seat.setSeatNumber(seatRequest.getSeatNumber());
        seat.setSeatType(seatRequest.getSeatType());
        seat.setPrice(seatRequest.getPrice());

        return seatRepository.save(seat);
    }
}
