package uab.eventos_backend.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uab.eventos_backend.request.CuentaBancariaDTO;
import uab.eventos_backend.services.CuentaBancariaService;

@RestController
@RequestMapping("/account")
public class CuentaBancariaEntityController {

    @Autowired
    private CuentaBancariaService cuentaBancariaService;

    @GetMapping("/bank/getCuentasBancarias")
    public ResponseEntity<?> getCuentasBancariasPorId(@RequestParam Long userId) {
        return ResponseEntity.ok(this.cuentaBancariaService.getAllCuentasBancarias(userId));
    }

    @PostMapping("/bank/addCuentaBancaria")
    public ResponseEntity<?> addCuentaBancaria(@Valid @RequestBody CuentaBancariaDTO cuentaBancaria) {
        return ResponseEntity.ok(this.cuentaBancariaService.agregarCuentaBancaria(cuentaBancaria));
    }

    @DeleteMapping("/bank/deleteCuentaBancaria")
    public ResponseEntity<?> deleteCuentaBancaria(@RequestParam Long idCuenta) {
        this.cuentaBancariaService.deleteCuentaBancaria(idCuenta);
        return ResponseEntity.ok(Boolean.TRUE);
    }
}
