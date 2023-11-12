package uab.eventos_backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import uab.eventos_backend.models.EGenero;
import uab.eventos_backend.models.ERole;
import uab.eventos_backend.models.RoleEntity;
import uab.eventos_backend.models.UserEntity;
import uab.eventos_backend.repositories.UserRepository;

import java.util.Set;

@SpringBootApplication
public class BackendApplication {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	CommandLineRunner init() {
		return args -> {
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
							.name(ERole.valueOf(ERole.SUPER_ADMIN.name()))
							.build()))
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
							.name(ERole.valueOf(ERole.ADMIN.name()))
							.build()))
					.build();

			UserEntity userEntity3 = UserEntity.builder()
					.email("santiago.quispe@uab.edu.bo")
					.password(passwordEncoder.encode("12345"))
					.nombre("Santiago")
					.apellidos("Quispe Mamani")
					.telefono("77563485")
					.genero(EGenero.HOMBRE)
					.perfil("default.png")
					.habilitado(Boolean.TRUE)
					.roles(Set.of(RoleEntity.builder()
							.name(ERole.valueOf(ERole.USER.name()))
							.build()))
					.build();

			this.userRepository.save(userEntity1);
			this.userRepository.save(userEntity2);
			this.userRepository.save(userEntity3);
		};
	}

}
