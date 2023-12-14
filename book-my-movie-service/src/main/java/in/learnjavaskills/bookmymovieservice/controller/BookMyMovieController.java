package in.learnjavaskills.bookmymovieservice.controller;

import in.learnjavaskills.bookmymovieservice.dto.MovieTicketBookingRequest;
import in.learnjavaskills.bookmymovieservice.service.InitiateMovieTicketBookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies/")
public class BookMyMovieController
{

    private final InitiateMovieTicketBookingService initiateMovieTicketBookingService;

    public BookMyMovieController(InitiateMovieTicketBookingService initiateMovieTicketBookingService) {
        this.initiateMovieTicketBookingService = initiateMovieTicketBookingService;
    }

    @PostMapping("book-ticket")
    ResponseEntity<String> bookMovieTicket(@RequestBody MovieTicketBookingRequest movieTicketBookingRequest) {
        return initiateMovieTicketBookingService.publishMovieTicketBookingMessage(movieTicketBookingRequest);
    }
}
