package pe.edu.cibertec.dawii.ms.usuarios.service;

import pe.edu.cibertec.dawii.ms.usuarios.entity.Permiso;

import java.util.List;
import java.util.Optional;

public interface PermisoService {

    List<Permiso> listarTodosPermisos();

    Permiso obtenerPorIdPermiso(Long id);

    Permiso crearPermiso(Permiso permiso);

    Optional<Permiso>  actualizar(Long id, Permiso permiso);
    void eliminar(Long id);
}
