package pl.pwr.ite.server.api.controller;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import pl.pwr.ite.model.entity.ForbiddenWord;
import pl.pwr.ite.model.entity.Message;
import pl.pwr.ite.server.api.dto.MessageDto;
import pl.pwr.ite.service.ForbiddenWordService;

import java.util.Iterator;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageController {

    private final KafkaTemplate<String, MessageDto> kafkaTemplate;

    private final ForbiddenWordService forbiddenWordService;


    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void sendMessage(@RequestBody MessageDto dto) {
        kafkaTemplate.send(new ProducerRecord<String, MessageDto>("main", dto));
    }
}
