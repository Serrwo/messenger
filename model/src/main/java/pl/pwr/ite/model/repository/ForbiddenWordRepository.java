package pl.pwr.ite.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pwr.ite.model.entity.ForbiddenWord;

import java.util.UUID;

public interface ForbiddenWordRepository extends JpaRepository<ForbiddenWord, UUID> {



}
