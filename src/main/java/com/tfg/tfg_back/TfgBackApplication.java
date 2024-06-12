package com.tfg.tfg_back;

import com.tfg.tfg_back.model.Rol;
import com.tfg.tfg_back.model.Usuario;
import com.tfg.tfg_back.model.UsuarioRol;
import com.tfg.tfg_back.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class TfgBackApplication implements CommandLineRunner {
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("http://localhost:4200")
						.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
						.allowedHeaders("*");
			}
		};
	}

	@Autowired
	private UsuarioService usuarioservice;


	public static void main(String[] args) {
		SpringApplication.run(TfgBackApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		/*Usuario usuario = new Usuario();
		usuario.setNombre("admin");
		usuario.setEmail("admin@gmail.com");
		usuario.setPassword("12345");
		usuario.setUsername("admin");

		Rol rol = new Rol();
		rol.setRolId(1L);
		rol.setNombre("ADMIN");

		Set<UsuarioRol> usuarioRoles = new HashSet<>();
		UsuarioRol usuarioRol = new UsuarioRol();
		usuarioRol.setRol(rol);
		usuarioRol.setUsuario(usuario);
		usuarioRoles.add(usuarioRol);

		Usuario usuarioguardado = usuarioservice.guardarUsuario(usuario, usuarioRoles);
		System.out.println(usuarioguardado.getUsername());*/

	}
}
