package uab.eventos_backend.services;

import uab.eventos_backend.exceptions.UserNotFoundException;
import uab.eventos_backend.models.UserEntity;

import java.util.List;

public interface SolicitudEntityService {

    boolean saveSolicitud(Long userId, Long eventoId) throws UserNotFoundException;

    boolean deleteSolicitud(Long userId, Long eventoId);

    List<UserEntity> getSolicitudesbyEvento(Long eventoId);

    boolean confirmarsolicitud(Long userId, Long eventoId) throws UserNotFoundException;

}
