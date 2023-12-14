package in.learnjavaskills.bookingprocessservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record MovieTicketBookedSuccessfully(String movieId,
                                            String user,
                                            String moviePassCode,
                                            @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                                            LocalDateTime messageGeneratedOn)
{}
