package pe.edu.cibertec.dawii.ms.usuarios.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.cibertec.dawii.ms.usuarios.entity.Rol;
import pe.edu.cibertec.dawii.ms.usuarios.entity.UsuarioRol;
import pe.edu.cibertec.dawii.ms.usuarios.repository.UsuarioRolRepository;
import pe.edu.cibertec.dawii.ms.usuarios.service.UsuarioRolService;

@Service
public class UsuarioRolServiceImpl implements UsuarioRolService {
    @Autowired
    private UsuarioRolRepository usuarioRolRepository;

	
	@Override
	public List<UsuarioRol> findAll() {
        return usuarioRolRepository.findAll();

	}
	@Override
	public UsuarioRol obtenerPorIdUsuarioRol(Long id) {
        Optional<UsuarioRol> usuarioRolOpt = usuarioRolRepository.findById(id);
        return usuarioRolOpt.orElse(null);

	}
	@Override
	public UsuarioRol save(UsuarioRol usuarioRol) {
		  usuarioRol.setFechaCreacion(LocalDateTime.now());
	        usuarioRol.setFechaActualizacion(LocalDateTime.now());
	        return usuarioRolRepository.save(usuarioRol);
	}

	@Override
	public void delete(Long id) {
		usuarioRolRepository.deleteById(id);
	}		
	

	@Override
	public Optional<UsuarioRol> actualizarUsuarioRol(UsuarioRol usuarioRol, Long id) {
		Optional<UsuarioRol> usuarioRolExistente = usuarioRolRepository.findById(id);

		 return usuarioRolExistente.map(ur -> {
	            ur.setUsuario(usuarioRol.getUsuario());
	            ur.setRol(usuarioRol.getRol());
	            ur.setFechaActualizacion(LocalDateTime.now());
	            return usuarioRolRepository.save(ur);
	        });
		 }

}
