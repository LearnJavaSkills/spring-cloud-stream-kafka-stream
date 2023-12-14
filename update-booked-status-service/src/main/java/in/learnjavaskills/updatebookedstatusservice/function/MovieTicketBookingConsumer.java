package in.learnjavaskills.updatebookedstatusservice.function;

import in.learnjavaskills.updatebookedstatusservice.dto.MovieTicketBookedSuccessfully;
import in.learnjavaskills.updatebookedstatusservice.repository.MovieTicketRepository;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class MovieTicketBookingConsumer
{

    private final MovieTicketRepository movieTicketRepository;

    @Autowired
    public MovieTicketBookingConsumer(MovieTicketRepository movieTicketRepository) {
        this.movieTicketRepository = movieTicketRepository;
    }

    @Bean("consumeMovieTickedBookingMessage")
    public Consumer<KStream<String, MovieTicketBookedSuccessfully>> consumeMovieTickedBookingMessage() {
        return movieTicketBookingMessageKStream -> {
          movieTicketBookingMessageKStream.foreach((messageKey, message) -> {
              System.out.println("Message Key : " + messageKey + " Message : " + message.toString());
              // Saving the successfully booked movie ticked into the data base.
              movieTicketRepository.save(message);
          });
        };
    }
}
