package uab.eventos_backend.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CuentaBancariaDTO {

    @NotBlank
    private String banco;

    @NotBlank
    private String cuenta;
}