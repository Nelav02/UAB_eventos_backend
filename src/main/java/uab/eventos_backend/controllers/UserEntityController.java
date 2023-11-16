package uab.eventos_backend.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uab.eventos_backend.exceptions.UserNotFoundException;
import uab.eventos_backend.request.CuentaBancariaDTO;
import uab.eventos_backend.request.RegisterUserEntity;
import uab.eventos_backend.services.UserEntityService;

@RestController
@RequestMapping("/api")
public class UserEntityController {

    @Autowired
    private UserEntityService userEntityService;

    @GetMapping("/management/getOne")
    public ResponseEntity<?> getUserByEmail(@RequestParam String email) {
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

    @GetMapping("/bank/getCuentasBancarias")
    public ResponseEntity<?> getCuentasBancariasPorId(@RequestParam Long userId) {
        return ResponseEntity.ok(this.userEntityService.getAllCuentasBancarias(userId));
    }

    @PostMapping("/bank/addCuentaBancaria")
    public ResponseEntity<?> addCuentaBancaria(@Valid @RequestBody CuentaBancariaDTO cuentaBancaria) {
        return ResponseEntity.ok(this.userEntityService.agregarCuentaBancaria(cuentaBancaria));
    }

    @DeleteMapping("/bank/deleteCuentaBancaria")
    public ResponseEntity<?> deleteCuentaBancaria(@RequestParam Long idCuenta) {
        this.userEntityService.deleteCuentaBancaria(idCuenta);
        return ResponseEntity.ok(Boolean.TRUE);
    }
}
