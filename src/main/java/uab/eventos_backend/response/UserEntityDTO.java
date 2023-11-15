package uab.eventos_backend.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uab.eventos_backend.request.CuentaBancariaDTO;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntityDTO {

    private String email;

    private String password;

    private String nombre;

    private String apellidos;

    private String telefono;

    private String genero;

    private List<CuentaBancariaDTO> cuentasBancarias;

    private String perfil;

    private Set<String> roles;
}
