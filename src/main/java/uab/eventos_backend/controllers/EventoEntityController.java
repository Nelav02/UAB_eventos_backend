package uab.eventos_backend.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uab.eventos_backend.exceptions.UserNotFoundException;
import uab.eventos_backend.request.HorarioDTO;
import uab.eventos_backend.response.EventoEntityDTO;
import uab.eventos_backend.services.EventoEntityService;

@RestController
@RequestMapping("/event")
public class EventoEntityController {

    @Autowired
    private EventoEntityService eventoEntityService;

    @PostMapping("/management/saveEvento")
    public ResponseEntity<?> saveEvento(@Valid @RequestBody EventoEntityDTO eventoEntityDTO) {
        return ResponseEntity.ok(this.eventoEntityService.saveEvento(eventoEntityDTO));
    }

    @GetMapping("/management/getAllEventos")
    public ResponseEntity<?> getAllEventos() {
        return ResponseEntity.ok(this.eventoEntityService.getAllEventos());
    }

    @GetMapping("/management/getEventosByFase")
    public ResponseEntity<?> getEventosPorFase(@RequestParam String fase) {
        return ResponseEntity.ok(this.eventoEntityService.getEventosByFase(fase));
    }

    @GetMapping("/management/getUsuariosByEvento")
    public ResponseEntity<?> getUsuariosByEvento(@RequestParam Long eventoId) {
        return ResponseEntity.ok(this.eventoEntityService.getUsuariosPorEvento(eventoId));
    }

    @PutMapping("/management/updateEvento")
    public ResponseEntity<?> updateEvento(@Valid @RequestBody EventoEntityDTO eventoEntityDTO, @RequestParam Long id) throws UserNotFoundException {
        return ResponseEntity.ok(this.eventoEntityService.updateEvento(eventoEntityDTO, id));
    }

    @PutMapping("/management/updateHorario")
    public ResponseEntity<?> updateHorario(@Valid @RequestBody HorarioDTO horarioDTO, @RequestParam Long id) throws UserNotFoundException {
        return ResponseEntity.ok(this.eventoEntityService.updateHorarioEvento(horarioDTO, id));
    }

    @DeleteMapping("/management/deleteEvento")
    public ResponseEntity<?> deleteEvento(@RequestParam Long id) {
        this.eventoEntityService.deleteEvento(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }
}
