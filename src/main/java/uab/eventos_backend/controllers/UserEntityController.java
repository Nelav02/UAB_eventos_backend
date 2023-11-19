package uab.eventos_backend.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uab.eventos_backend.exceptions.UserNotFoundException;
import uab.eventos_backend.models.UserEntity;
import uab.eventos_backend.models.View;
import uab.eventos_backend.request.CuentaBancariaDTO;
import uab.eventos_backend.request.RegisterUserEntity;
import uab.eventos_backend.services.UserEntityService;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserEntityController {

    @Autowired
    private UserEntityService userEntityService;

    @GetMapping("/management/getOne")
    //@JsonView(View.UserView.class)
    public ResponseEntity<?> getUserByEmail(@RequestParam String email) throws UserNotFoundException, JsonProcessingException {
        /*Optional<UserEntity> user = this.userEntityService.getUserByEmail(email);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper
                .writerWithView(View.UserView.class)
                .writeValueAsString(user.get());*/
        return ResponseEntity.ok(this.userEntityService.getUserByEmail(email));
    }

    @GetMapping("/management/getAll")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(this.userEntityService.getAllUsers());
    }

    @PutMapping("/management/update")
    public ResponseEntity<?> updateUser(@Valid @RequestBody RegisterUserEntity request, @RequestParam Long id) throws UserNotFoundException {
        return ResponseEntity.ok(this.userEntityService.updateUser(request, id));
    }

    @DeleteMapping("/management/delete")
    public ResponseEntity<?> deleteUser(@RequestParam Long id) {
        this.userEntityService.deleteUser(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }
}
