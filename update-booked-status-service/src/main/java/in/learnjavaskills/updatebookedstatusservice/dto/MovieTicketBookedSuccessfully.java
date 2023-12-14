package in.learnjavaskills.updatebookedstatusservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import java.time.LocalDateTime;

public record MovieTicketBookedSuccessfully(String movieId,
                                            String user,
                                            String moviePassCode,
                                            @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                                            @JsonDeserialize(using = LocalDateTimeDeserializer.class)
                                            LocalDateTime messageGeneratedOn)
{}
