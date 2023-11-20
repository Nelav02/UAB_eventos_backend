package uab.eventos_backend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "eventos")
public class EventoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @NotBlank
    private String requerimientos;

    @NotNull
    @ManyToMany(
            fetch = FetchType.EAGER,
            targetEntity = FaseEntity.class,
            cascade = CascadeType.PERSIST
    )
    @JoinTable(
            name = "evento_etapa",
            joinColumns = @JoinColumn(name = "evento_id"),
            inverseJoinColumns = @JoinColumn(name = "etapa_id")
    )
    private Set<FaseEntity> fase;

    @JsonBackReference
    @ManyToMany(mappedBy = "eventos", fetch = FetchType.LAZY)
    private List<UserEntity> usuarios = new ArrayList<>();
}
