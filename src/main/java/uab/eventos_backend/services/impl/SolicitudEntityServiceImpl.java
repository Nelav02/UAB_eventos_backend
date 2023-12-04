package uab.eventos_backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uab.eventos_backend.exceptions.UserNotFoundException;
import uab.eventos_backend.models.EventoEntity;
import uab.eventos_backend.models.SolicitudEntity;
import uab.eventos_backend.models.UserEntity;
import uab.eventos_backend.repositories.EventoRepository;
import uab.eventos_backend.repositories.SolicitudRepository;
import uab.eventos_backend.repositories.UserRepository;
import uab.eventos_backend.services.SolicitudEntityService;

import java.util.List;

@Service
public class SolicitudEntityServiceImpl implements SolicitudEntityService {

    @Autowired
    private SolicitudRepository solicitudRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventoRepository eventoRepository;

    @Override
    public boolean saveSolicitud(Long userId, Long eventoId) throws UserNotFoundException {

        UserEntity user = this.userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Usuario con id: '" + userId + "' no encontrado."));

        EventoEntity evento = this.eventoRepository.findById(eventoId)
                .orElseThrow(() -> new UserNotFoundException("Evento con id: '" + eventoId + "' no encontrado."));

        if (this.solicitudRepository.existsByUserIdAndEventoId(userId, eventoId)) {
            return false;
        }

        SolicitudEntity solicitud = SolicitudEntity.builder()
                .user(user)
                .evento(evento)
                .confirmado(false)
                .build();

        this.solicitudRepository.save(solicitud);

        return true;
    }

    @Transactional
    @Override
    public boolean deleteSolicitud(Long userId, Long eventoId) {
        this.solicitudRepository.deleteByUserIdAndEventoId(userId, eventoId);
        return true;
    }

    @Override
    public List<UserEntity> getSolicitudesbyEvento(Long eventoId) {

        List<SolicitudEntity> solicitudes = this.solicitudRepository.findByEventoId(eventoId);

        return solicitudes.stream()
                .map(SolicitudEntity::getUser)
                .toList();
    }

    @Transactional
    @Override
    public boolean confirmarsolicitud(Long userId, Long eventoId) throws UserNotFoundException {

        SolicitudEntity solicitud = this.solicitudRepository.findByUserIdAndEventoId(userId, eventoId);

        EventoEntity evento = this.eventoRepository.findById(eventoId)
                .orElseThrow(() -> new UserNotFoundException("Evento con id: '" + eventoId + "' no encontrado."));

        evento.getUsuarios().add(solicitud.getUser());

        solicitud.setConfirmado(true);

        this.eventoRepository.save(evento);
        this.solicitudRepository.save(solicitud);

        return true;
    }
}
