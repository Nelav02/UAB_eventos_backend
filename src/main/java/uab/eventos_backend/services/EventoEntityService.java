package uab.eventos_backend.services;

import uab.eventos_backend.exceptions.UserNotFoundException;
import uab.eventos_backend.models.EventoEntity;
import uab.eventos_backend.models.UserEntity;
import uab.eventos_backend.request.HorarioDTO;
import uab.eventos_backend.response.EventoEntityDTO;

import java.util.List;
import java.util.Optional;

public interface EventoEntityService {

    Optional<EventoEntity> saveEvento(EventoEntityDTO eventoEntityDTO);

    List<UserEntity> getUsuariosPorEvento(Long eventoId);

    List<EventoEntity> getAllEventos();

    List<EventoEntity> getEventosByFase(String fase);

    Optional<EventoEntity>  updateEvento(EventoEntityDTO eventoEntityDTO, Long id) throws UserNotFoundException;

    Optional<EventoEntity> updateHorarioEvento(HorarioDTO horario, Long id) throws UserNotFoundException;

    void deleteEvento(Long id);
}
