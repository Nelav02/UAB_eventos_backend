package uab.eventos_backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import uab.eventos_backend.models.*;
import uab.eventos_backend.repositories.EventoRepository;
import uab.eventos_backend.repositories.UserRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SpringBootApplication
public class BackendApplication {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EventoRepository eventoRepository;

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	CommandLineRunner init() {
		return args -> {

			EventoEntity evento1 = EventoEntity.builder()
					.titulo("Cultura General")
					.lugar("Coliseo UAB")
					.fecha(LocalDate.of(2023, 9, 18))
					.horaInicio(LocalTime.of(11, 35, 0))
					.horaFinal(LocalTime.of(13, 0, 0))
					.requerimientos("4 microfonos, 2 pantallas grandes, sonido")
					.fase(Set.of(FaseEntity.builder()
							.name(EFase.valueOf(EFase.ASIGNADO.name()))
							.build()))
					.usuarios(List.of())
					.build();

			this.eventoRepository.save(evento1);

			UserEntity userEntity1 = UserEntity.builder()
					.email("valentin.lluta@uab.edu.bo")
					.password(passwordEncoder.encode("12345"))
					.nombre("Valentin")
					.apellidos("Lluta Choque")
					.telefono("78943512")
					.genero(EGenero.HOMBRE)
					.perfil("default.png")
					.habilitado(Boolean.TRUE)
					.roles(Set.of(RoleEntity.builder()
							.name(ERole.valueOf(ERole.ADMIN.name()))
							.build()))
					.eventos(List.of())
					.build();

			UserEntity userEntity2 = UserEntity.builder()
					.email("kelly.arenas@uab.edu.bo")
					.password(passwordEncoder.encode("12345"))
					.nombre("Kelly Ester")
					.apellidos("Arenas Alarcón")
					.telefono("78945632")
					.genero(EGenero.MUJER)
					.perfil("default.png")
					.habilitado(Boolean.TRUE)
					.roles(Set.of(RoleEntity.builder()
							.name(ERole.valueOf(ERole.USER.name()))
							.build()))
					.eventos(List.of(evento1))
					.build();

			this.userRepository.save(userEntity1);
			this.userRepository.save(userEntity2);
		};
	}

}
