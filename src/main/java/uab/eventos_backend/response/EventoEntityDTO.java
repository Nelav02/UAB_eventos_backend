package uab.eventos_backend.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uab.eventos_backend.models.UserEntity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventoEntityDTO {

    @NotBlank
    private String titulo;

    @NotBlank
    private String lugar;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;

    @NotNull
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime horaInicio;

    @NotNull
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime horaFinal;

    @NotNull
    private String requerimientos;

    @NotNull
    private Set<String> fase;

    private List<UserEntity> usuarios;
}
