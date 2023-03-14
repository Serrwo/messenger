package pl.pwr.ite.server.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pwr.ite.kafka.Topics;
import pl.pwr.ite.server.api.dto.MessageDto;
import pl.pwr.ite.server.api.service.MessageFacade;
import pl.pwr.ite.server.kafka.MessageKafkaService;

import java.util.Collection;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageController {

    private final MessageFacade messageFacade;

    private final MessageKafkaService messageKafkaService;

    @GetMapping
    public ResponseEntity<Collection<MessageDto>> list() {
        return ResponseEntity.ok(messageFacade.listAllDto());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void sendMessage(@RequestBody MessageDto dto) {
        messageKafkaService.produce(Topics.CENSORSHIP_DEFAULT, dto);
    }
}
