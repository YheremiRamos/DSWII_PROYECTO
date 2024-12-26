package pe.edu.cibertec.dawii.ms.usuarios.service;

import java.util.List;
import java.util.Optional;

import pe.edu.cibertec.dawii.ms.usuarios.entity.Usuario;

public interface UsuarioService {

	
	List<Usuario> obtenerTodosUsuarios();

	Optional<Usuario> obtenerUsuarioPorId(Long id);

	Optional<Usuario> findByEmail(String email);

	Usuario crearUsuario(Usuario usuario);

	Optional<Usuario> actualizarUsuario(Usuario usuario, Long id);

	void eliminarUsuario(Long id);
}
