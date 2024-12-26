package pe.edu.cibertec.dawii.ms.usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.dawii.ms.usuarios.entity.Rol;
import pe.edu.cibertec.dawii.ms.usuarios.entity.Usuario;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
    Optional<Rol> findById(Long id);


}
