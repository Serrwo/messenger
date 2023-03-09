package pl.pwr.ite.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pwr.ite.model.entity.Topic;

import java.util.UUID;

public interface TopicRepository extends JpaRepository<Topic, UUID> {
}
