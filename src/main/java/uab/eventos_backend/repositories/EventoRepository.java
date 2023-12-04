package uab.eventos_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uab.eventos_backend.models.EventoEntity;
import uab.eventos_backend.models.FaseEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventoRepository extends JpaRepository<EventoEntity, Long> {

    List<EventoEntity> findByFase(FaseEntity fase);

    Optional<EventoEntity> findById(Long eventoId);
}
