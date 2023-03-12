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

    @ManyToOne
    @JoinColumn(nullable = false)
    private Topic topic;

    @Column(nullable = false, insertable = false, updatable = false, name = "topic_id")
    private UUID topicId;

//    @Column(nullable = false)
//    private String topic;

    @Column(nullable = false)
    private LocalDateTime time;

    public void setTopic(Topic topic) {
        this.topic = topic;
        this.topicId = topic == null ? null : topic.getId();
    }
}
