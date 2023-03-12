package pl.pwr.ite.server.api.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class TopicDto {

    private UUID id;

    private String name;
}
