package in.learnjavaskills.bookmymovieservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record MovieTicketBookingMessage(String movieName, byte screenNumber,
                                        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                                        LocalDateTime movieShowTime,
                                        String seatNumber,
                                        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                                        LocalDateTime messageGeneratedOn,
                                        String users)
{
    public MovieTicketBookingMessage(MovieTicketBookingRequest movieTicketBookingRequest) {
        this (movieTicketBookingRequest.getMovieName(),
                movieTicketBookingRequest.getScreenNumber(),
                movieTicketBookingRequest.getMovieShowTime(),
                movieTicketBookingRequest.getSeatNumber(),
                LocalDateTime.now(),
                movieTicketBookingRequest.getUser());
    }
}
