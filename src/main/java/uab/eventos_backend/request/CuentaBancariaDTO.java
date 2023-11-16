package uab.eventos_backend.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CuentaBancariaDTO {

    @NotNull
    private Long userId;

    @NotBlank
    private String banco;

    @NotBlank
    private String cuenta;
}