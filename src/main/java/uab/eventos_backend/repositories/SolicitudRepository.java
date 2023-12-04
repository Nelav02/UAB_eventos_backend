package uab.eventos_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uab.eventos_backend.models.SolicitudEntity;

import java.util.List;

@Repository
public interface SolicitudRepository extends JpaRepository<SolicitudEntity, Long> {

    List<SolicitudEntity> findByEventoId(Long eventoId);

    boolean existsByUserIdAndEventoId(Long userId, Long eventoId);

    SolicitudEntity findByUserIdAndEventoId(Long userId, Long eventoId);

    void deleteByUserIdAndEventoId(Long userId, Long eventoId);
}