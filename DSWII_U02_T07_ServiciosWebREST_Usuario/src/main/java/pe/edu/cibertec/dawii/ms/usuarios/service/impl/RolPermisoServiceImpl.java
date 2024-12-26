package pe.edu.cibertec.dawii.ms.usuarios.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.cibertec.dawii.ms.usuarios.entity.Permiso;
import pe.edu.cibertec.dawii.ms.usuarios.entity.Rol;
import pe.edu.cibertec.dawii.ms.usuarios.entity.RolPermiso;
import pe.edu.cibertec.dawii.ms.usuarios.repository.PermisoRepository;
import pe.edu.cibertec.dawii.ms.usuarios.repository.RolPermisoRepository;
import pe.edu.cibertec.dawii.ms.usuarios.repository.RolRepository;
import pe.edu.cibertec.dawii.ms.usuarios.service.RolPermisoService;

@Service
public class RolPermisoServiceImpl implements RolPermisoService {

	   @Autowired
	    private RolPermisoRepository rolPermisoRepository;

	    @Autowired
	    private PermisoRepository permisoRepository;

	    @Autowired
	    private RolRepository rolRepository;

	    // Listar todos los RolPermiso
	    @Override
	    public List<RolPermiso> listarRolPermisos() {
	        return rolPermisoRepository.findAll();
	    }

	    // Obtener un RolPermiso por ID
	    @Override
	    public RolPermiso obtenerPorIdRolPermiso(Long id) {
	        Optional<RolPermiso> rolPermisoOpt = rolPermisoRepository.findById(id);
	        return rolPermisoOpt.orElse(null);
	    }




	// Actualizar un RolPermiso existente
	    @Override
	    public Optional<RolPermiso> actualizar(RolPermiso rolPermiso, Long idRol) {
	        Optional<RolPermiso> rolPermisoExistente = rolPermisoRepository.findById(idRol);

	       return rolPermisoExistente.map(r -> {
			  r.setPermiso(rolPermiso.getPermiso());
			   r.setRol(rolPermiso.getRol());
			   r.setFechaCreacion(LocalDateTime.now());
			   return rolPermisoRepository.save(r); });
	    }

	    // Eliminar un RolPermiso
	    @Override
	    public void eliminarRolPermiso(Long id) {
	        rolPermisoRepository.deleteById(id);
	    }
	}