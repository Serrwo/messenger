package pl.pwr.ite.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Table
@Entity
@Getter
@Setter
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
public class Message extends EntityBase {

    @Column(nullable = false, length = 1024)
    private String message;

    @Column(nullable = false)
    private String topic;

    @Column(nullable = false)
    private LocalDateTime time;
}
