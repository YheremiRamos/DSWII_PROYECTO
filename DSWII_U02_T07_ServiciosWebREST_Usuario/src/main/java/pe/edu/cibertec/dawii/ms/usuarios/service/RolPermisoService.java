package pe.edu.cibertec.dawii.ms.usuarios.service;

import java.util.List;
import java.util.Optional;

import pe.edu.cibertec.dawii.ms.usuarios.entity.RolPermiso;

public interface RolPermisoService {
	 List<RolPermiso> listarRolPermisos(); // Lista todos los registros de RolPermiso

	    RolPermiso obtenerPorIdRolPermiso(Long id); // Obtiene un RolPermiso por su ID

	// Actualizar un RolPermiso existente
	Optional<RolPermiso> actualizar(RolPermiso rolPermiso, Long idRolPermiso);

	void eliminarRolPermiso(Long id); // Elimina un RolPermiso por su ID
}
