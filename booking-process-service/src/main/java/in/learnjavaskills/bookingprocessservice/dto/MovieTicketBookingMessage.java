package in.learnjavaskills.bookingprocessservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import java.time.LocalDateTime;

public record MovieTicketBookingMessage(String movieName, byte screenNumber,
                                        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                                        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
                                        LocalDateTime movieShowTime,
                                        String seatNumber,
                                        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                                        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
                                        LocalDateTime messageGeneratedOn,
                                        String users)
{}
