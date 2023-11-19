package uab.eventos_backend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(View.UserView.class)
    private Long id;

    @Email
    @NotBlank
    @JsonView(View.UserView.class)
    private String email;

    @NotBlank
    @JsonView(View.UserView.class)
    private String password;

    @NotBlank
    @JsonView(View.UserView.class)
    private String nombre;

    @NotBlank
    @JsonView(View.UserView.class)
    private String apellidos;

    @NotBlank
    @JsonView(View.UserView.class)
    private String telefono;

    @NotNull
    @JsonView(View.UserView.class)
    @Enumerated(EnumType.STRING)
    private EGenero genero;

    @JsonView(View.UserView.class)
    @OneToMany(
            targetEntity = CuentaBancariaEntity.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference
    private List<CuentaBancariaEntity> cuentasBancarias = new ArrayList<>();

    @JsonView(View.UserView.class)
    private boolean habilitado;

    @JsonView(View.UserView.class)
    private String perfil;

    @NotNull
    @JsonView(View.UserView.class)
    @ManyToMany(
            fetch = FetchType.EAGER,
            targetEntity = RoleEntity.class,
            cascade = CascadeType.PERSIST
    )
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleEntity> roles;

    @JsonView({View.EventoView.class, View.UserView.class})
    //@JsonManagedReference
    @JsonBackReference
    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.MERGE
    )
    @JoinTable(
            name = "usuario_evento",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "evento_id", referencedColumnName = "id")
    )
    private List<EventoEntity> eventos = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_".concat(role.getName().name())))
                .collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
