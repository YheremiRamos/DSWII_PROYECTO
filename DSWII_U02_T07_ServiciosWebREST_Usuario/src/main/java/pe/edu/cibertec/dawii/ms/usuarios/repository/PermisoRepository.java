package pe.edu.cibertec.dawii.ms.usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.dawii.ms.usuarios.entity.Permiso;

@Repository
public interface PermisoRepository extends JpaRepository<Permiso, Long> {

}
