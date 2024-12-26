package pe.edu.cibertec.dawii.ms.usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.cibertec.dawii.ms.usuarios.entity.RolPermiso;

@Repository
public interface RolPermisoRepository extends JpaRepository<RolPermiso, Long>{
	

}
