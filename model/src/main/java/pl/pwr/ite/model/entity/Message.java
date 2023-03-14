package pl.pwr.ite.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Table
@Entity
@Getter
@Setter
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
public class Message extends EntityBase {

    @Column(nullable = false, length = 1024)
    private String message;

    @Column(nullable = false)
    private LocalDateTime time;

    @Column(nullable = false, length = 100)
    private String username;
}
