package uab.eventos_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uab.eventos_backend.exceptions.ResourceAlreadyExistsException;
import uab.eventos_backend.exceptions.UserNotFoundException;
import uab.eventos_backend.services.SolicitudEntityService;
import uab.eventos_backend.services.impl.SolicitudEntityServiceImpl;

@RestController
@RequestMapping("/request")
public class SolicitudController {

    @Autowired
    private SolicitudEntityService solicitudEntityService;

    @PostMapping("/management/saveSolicitud")
    public ResponseEntity<?> saveSolicitud(@RequestParam Long userId, @RequestParam Long eventoId) throws UserNotFoundException {
        return ResponseEntity.ok(this.solicitudEntityService.saveSolicitud(userId, eventoId));
    }

    @GetMapping("/management/getUsersByEventoId")
    public ResponseEntity<?> getUsuariosByEventoId(@RequestParam Long eventoId) {
        return ResponseEntity.ok(this.solicitudEntityService.getSolicitudesbyEvento(eventoId));
    }

    @PostMapping("/management/confirmarSolicitud")
    public ResponseEntity<?> confirmarSolicitud(@RequestParam Long userId, @RequestParam Long eventoId) throws UserNotFoundException {
        return ResponseEntity.ok(this.solicitudEntityService.confirmarsolicitud(userId, eventoId));
    }

    @DeleteMapping("/management/deleteSolicitud")
    public ResponseEntity<?> deleteSolicitud(@RequestParam Long userId, @RequestParam Long eventoId) {
        return ResponseEntity.ok(this.solicitudEntityService.deleteSolicitud(userId, eventoId));
    }
}
