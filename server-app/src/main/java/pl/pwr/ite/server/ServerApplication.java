package pl.pwr.ite.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner(KafkaTemplate<String, String> kafkaTemplate) {
//        return args -> {
//            for(int i = 0; i < 10_000; i++) {
//                Thread.sleep(100);
//                kafkaTemplate.send("main", "hello kafka" + i);
//            }
//        };
//    }
}