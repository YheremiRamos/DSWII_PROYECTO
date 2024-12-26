package pe.edu.cibertec.dawii.ms.usuarios.service;

import pe.edu.cibertec.dawii.ms.usuarios.entity.Rol;

import java.util.List;
import java.util.Optional;

public interface RolService {

    List<Rol> findAll();
    Optional<Rol> findById(Long id);
    Rol save(Rol rol);
    void delete(Long id);
    Optional<Rol> actualizarRol(Rol rol, Long id);
}
