package pe.edu.cibertec.dawii.ms.usuarios.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pe.edu.cibertec.dawii.ms.usuarios.entity.Permiso;
import pe.edu.cibertec.dawii.ms.usuarios.repository.PermisoRepository;
import pe.edu.cibertec.dawii.ms.usuarios.service.PermisoService;


@Service
@RequiredArgsConstructor
public class PermisoServiceImpl implements PermisoService {
	
	 @Autowired
	    private PermisoRepository permisoRepository;
	
	public List<Permiso> listarTodosPermisos() {
    return permisoRepository.findAll();

	}

	@Override
	public Permiso obtenerPorIdPermiso(Long id) {
		  Optional<Permiso> permisoOpt = permisoRepository.findById(id);
	        return permisoOpt.orElse(null);
	}

	@Override
	public Permiso crearPermiso(Permiso permiso) {
        return permisoRepository.save(permiso);

	}

	@Override
	public Optional<Permiso> actualizar(Long id, Permiso permiso) {
		Optional<Permiso> permisoExistente = permisoRepository.findById(id);
        return permisoExistente.map(p -> {
            p.setNombre(permiso.getNombre());
            p.setDescripcion(permiso.getDescripcion());
            p.setFechaActualizacion(LocalDateTime.now());
            return permisoRepository.save(p);
        });
	}

	@Override
	public void eliminar(Long id) {
        permisoRepository.deleteById(id);
		
	}
   

}
