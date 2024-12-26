package pe.edu.cibertec.dawii.ms.usuarios.service;

import java.util.List;
import java.util.Optional;

import pe.edu.cibertec.dawii.ms.usuarios.entity.Permiso;
import pe.edu.cibertec.dawii.ms.usuarios.entity.UsuarioRol;

public interface UsuarioRolService {
	
		List<UsuarioRol> findAll();
	    UsuarioRol obtenerPorIdUsuarioRol(Long id);
	    UsuarioRol save(UsuarioRol usuarioRol);
	    void delete(Long id);
	    Optional<UsuarioRol> actualizarUsuarioRol(UsuarioRol usuarioRol, Long id);
}
