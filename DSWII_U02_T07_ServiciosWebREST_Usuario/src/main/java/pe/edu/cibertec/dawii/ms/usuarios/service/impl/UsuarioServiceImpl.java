package pe.edu.cibertec.dawii.ms.usuarios.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.cibertec.dawii.ms.usuarios.entity.Usuario;
import pe.edu.cibertec.dawii.ms.usuarios.entity.UsuarioRol;
import pe.edu.cibertec.dawii.ms.usuarios.repository.UsuarioRepository;
import pe.edu.cibertec.dawii.ms.usuarios.rmq.Notificacion;
import pe.edu.cibertec.dawii.ms.usuarios.rmq.NotificacionSender;
import pe.edu.cibertec.dawii.ms.usuarios.service.UsuarioService;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private NotificacionSender notificacionSender;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public List<Usuario> obtenerTodosUsuarios() {
		return usuarioRepository.findAll();
	}

	@Override
	public Optional<Usuario> obtenerUsuarioPorId(Long id) {
		return usuarioRepository.findById(id);
	}

	@Override
	public Optional<Usuario> findByEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}

	@Override
	public Usuario crearUsuario(Usuario usuario) {
		// Registro síncrono, depende de la BD
		usuario.setFechaCreacion(LocalDateTime.now()); // Seteamos la fecha de creación
		Usuario usuarioRegistrado = usuarioRepository.save(usuario);

		// Registro asíncrono - Dispara y olvida
		Notificacion notificacion = Notificacion.builder()
				.asunto("Usuario creado con éxito")
				.contenido("Su usuario fue creado con éxito...")
				.email(usuario.getEmail())
				.build();
		notificacionSender.enviarNotificacion(notificacion);

		return usuarioRegistrado;
	}

	@Override
	public Optional<Usuario> actualizarUsuario(Usuario usuario, Long id) {
		Optional<Usuario> usuarioEncontrado = usuarioRepository.findById(id);
		return usuarioEncontrado.map(usuarioExistente -> {
			usuarioExistente.setNombreUsuario(usuario.getNombreUsuario());
			usuarioExistente.setContrasena(usuario.getContrasena());
			usuarioExistente.setEmail(usuario.getEmail());
			usuarioExistente.setActivo(usuario.getActivo());
			usuarioExistente.setFechaActualizacion(LocalDateTime.now());

			return usuarioRepository.save(usuarioExistente);
		});
	}

	@Override
	public void eliminarUsuario(Long id) {
		usuarioRepository.deleteById(id);
	}

	
}
