package uab.eventos_backend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
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
    @JsonView(View.EventoView.class)
    private Long id;

    @NotBlank
    @JsonView(View.EventoView.class)
    private String titulo;

    @NotBlank
    @JsonView(View.EventoView.class)
    private String lugar;

    @NotNull
    @JsonView(View.EventoView.class)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;

    @NotNull
    @JsonView(View.EventoView.class)
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime horaInicio;

    @NotNull
    @JsonView(View.EventoView.class)
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime horaFinal;

    @NotBlank
    @JsonView(View.EventoView.class)
    private String requerimientos;

    @NotNull
    @JsonView(View.EventoView.class)
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

    @JsonView({View.UserView.class, View.EventoView.class})
    //@JsonBackReference
    @JsonManagedReference
    @ManyToMany(mappedBy = "eventos", fetch = FetchType.EAGER)
    private List<UserEntity> usuarios = new ArrayList<>();
}
