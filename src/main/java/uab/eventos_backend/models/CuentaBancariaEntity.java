package uab.eventos_backend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cuentas_bancarias")
public class CuentaBancariaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String banco;

    @NotBlank
    private String cuenta;

    @ManyToOne(targetEntity = UserEntity.class)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private UserEntity user;
}
