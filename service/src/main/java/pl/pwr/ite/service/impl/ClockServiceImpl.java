package pl.pwr.ite.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pwr.ite.service.ClockService;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ClockServiceImpl implements ClockService {

    @Override
    public LocalDateTime getCurrentTime() {
        return LocalDateTime.now();
    }
}
