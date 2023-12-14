package in.learnjavaskills.bookingprocessservice.function;

import in.learnjavaskills.bookingprocessservice.dto.MovieTicketBookedSuccessfully;
import in.learnjavaskills.bookingprocessservice.dto.MovieTicketBookingMessage;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

@Configuration
public class MovieTicketBookingProcess
{

    @Bean("processMovieTicketBooking")
    public Function<KStream<String, MovieTicketBookingMessage>, KStream<String, MovieTicketBookedSuccessfully>> processMovieTicketBooking() {
        return movieBookingRequestMessageKStream -> movieBookingRequestMessageKStream.map( (messageKey, movieBookingRequestMessage) -> {
            System.out.println("New message arrived with key : " + messageKey + " and values : " + movieBookingRequestMessage.toString());
            Optional<MovieTicketBookedSuccessfully> movieTicketBookedSuccessfully = bookMovieTicket(movieBookingRequestMessage);
            return new KeyValue<String, Optional<MovieTicketBookedSuccessfully>>(messageKey, movieTicketBookedSuccessfully);
        }).filter((messageKey, movieTicketBookedSuccessfullyOptional) -> {
            if (movieTicketBookedSuccessfullyOptional.isPresent()) {
                MovieTicketBookedSuccessfully movieTicketBookedSuccessfully = movieTicketBookedSuccessfullyOptional.get();
                System.out.println("Booking movie ticket for movie id : " + movieTicketBookedSuccessfully.movieId());
                return  true;
            } return false;
        }).map((messageKey, movieTicketBookedSuccessfully) -> new KeyValue<>(messageKey, movieTicketBookedSuccessfully.get()));
    }

    private Optional<MovieTicketBookedSuccessfully> bookMovieTicket(MovieTicketBookingMessage movieTicketBookingMessage) {
        if (Objects.isNull(movieTicketBookingMessage))
            return Optional.empty();

        Optional<String> moviePassCode = generateMoviePassCode(movieTicketBookingMessage);
        if (!moviePassCode.isPresent())
            return Optional.empty();

        MovieTicketBookedSuccessfully movieTicketBookedSuccessfully = new MovieTicketBookedSuccessfully(movieTicketBookingMessage.movieName() + UUID.randomUUID().toString(),
                movieTicketBookingMessage.users(),
                moviePassCode.get(),
                LocalDateTime.now());
        return Optional.of(movieTicketBookedSuccessfully);
    }

    private Optional<String> generateMoviePassCode(MovieTicketBookingMessage movieTicketBookingMessage) {
        if (Objects.isNull(movieTicketBookingMessage))
            return Optional.empty();

        String code = movieTicketBookingMessage.movieName() + " " + movieTicketBookingMessage.users()
                      + " " + movieTicketBookingMessage.seatNumber();

        try  {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] digest = messageDigest.digest(code.getBytes(StandardCharsets.UTF_8));
            return Optional.of(Base64.getEncoder().encodeToString(digest));
        }
        catch (NoSuchAlgorithmException e) {
            return Optional.empty();
        }
    }
}
