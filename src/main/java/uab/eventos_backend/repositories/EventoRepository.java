package uab.eventos_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uab.eventos_backend.models.EventoEntity;

@Repository
public interface EventoRepository extends JpaRepository<EventoEntity, Long> {
}
