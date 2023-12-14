package in.learnjavaskills.bookmymovieservice.service;

import in.learnjavaskills.bookmymovieservice.dto.MovieTicketBookingMessage;
import in.learnjavaskills.bookmymovieservice.dto.MovieTicketBookingRequest;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import java.util.Objects;

@Service
public class InitiateMovieTicketBookingService
{

    private final StreamBridge streamBridge;

    private final String topicName = "movie-ticket-request-topic";

    public InitiateMovieTicketBookingService(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    /**
     * Publishing message to the kafka topic
     * @param movieTicketBookingRequest
     * @return response
     */
    public ResponseEntity<String> publishMovieTicketBookingMessage(MovieTicketBookingRequest movieTicketBookingRequest) {
        if (Objects.isNull(movieTicketBookingRequest))
            return ResponseEntity.badRequest().body("Movie Ticket Booking Request must be non null");

        MovieTicketBookingMessage movieTicketBookingMessage = new MovieTicketBookingMessage(movieTicketBookingRequest);
        Message<MovieTicketBookingMessage> movieTicketBookingMessageMessage = generateMessage(movieTicketBookingMessage);
        boolean isMessageSend = streamBridge.send(topicName, movieTicketBookingMessageMessage, MimeTypeUtils.APPLICATION_JSON);
        if (isMessageSend)
            return ResponseEntity.ok("Movie booking ticket initiated success");
        else return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                .body("Unable to send message to kafka topic");
    }


    /**
     * Generate message to send to the kafka topic
     * @param movieTicketBookingMessage
     * @return Message
     */
    private Message<MovieTicketBookingMessage> generateMessage(MovieTicketBookingMessage movieTicketBookingMessage) {
        if (Objects.isNull(movieTicketBookingMessage))
            return null;

        return MessageBuilder.withPayload(movieTicketBookingMessage)
                .setHeader(KafkaHeaders.KEY, "YOUR_KEY_HERE".getBytes())
                .build();
    }
}
