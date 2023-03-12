package pl.pwr.ite.server.api.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageDto {

    private String message;

    private TopicDto topic;

    private LocalDateTime time;
}
