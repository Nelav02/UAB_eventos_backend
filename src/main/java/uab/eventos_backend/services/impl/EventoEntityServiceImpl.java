package uab.eventos_backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uab.eventos_backend.exceptions.UserNotFoundException;
import uab.eventos_backend.models.EFase;
import uab.eventos_backend.models.EventoEntity;
import uab.eventos_backend.models.FaseEntity;
import uab.eventos_backend.repositories.EventoRepository;
import uab.eventos_backend.repositories.FaseRepository;
import uab.eventos_backend.response.EventoEntityDTO;
import uab.eventos_backend.services.EventoEntityService;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EventoEntityServiceImpl implements EventoEntityService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private FaseRepository faseRepository;

    @Override
    public Optional<EventoEntity> saveEvento(EventoEntityDTO eventoDTO) {

        Set<FaseEntity> setFases = eventoDTO.getFase().stream()
                .map(fase -> {
                    Optional<FaseEntity> faseExistente = this.faseRepository.findByName(EFase.valueOf(fase));
                    return faseExistente.orElseGet(() -> FaseEntity.builder()
                            .name(EFase.valueOf(fase))
                            .build());
                })
                .collect(Collectors.toSet());

        EventoEntity newEvento = EventoEntity.builder()
                .titulo(eventoDTO.getTitulo())
                .lugar(eventoDTO.getLugar())
                .fecha(eventoDTO.getFecha())
                .horaInicio(eventoDTO.getHoraInicio())
                .horaFinal(eventoDTO.getHoraFinal())
                .requerimientos(eventoDTO.getRequerimientos())
                .fase(setFases)
                .usuarios(List.of())
                .build();

        return Optional.of(this.eventoRepository.save(newEvento));
    }

    @Override
    public List<EventoEntity> getAllEventos() {
        return this.eventoRepository.findAll();
    }

    @Override
    public Optional<EventoEntity> updateEvento(EventoEntityDTO eventoDTO, Long id) throws UserNotFoundException {

        EventoEntity evento = this.eventoRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuario con id: '" + id + "' no encontrado."));

        evento.setTitulo(evento.getTitulo());
        evento.setLugar(eventoDTO.getLugar());
        evento.setFecha(eventoDTO.getFecha());
        evento.setHoraInicio(eventoDTO.getHoraInicio());
        evento.setHoraFinal(eventoDTO.getHoraFinal());
        evento.setRequerimientos(evento.getRequerimientos());


        return Optional.of(this.eventoRepository.save(evento));
    }

    @Override
    public void deleteEvento(Long id) {
        this.eventoRepository.deleteById(id);
    }
}
