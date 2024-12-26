package pe.edu.cibertec.dawii.ms.usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.cibertec.dawii.ms.usuarios.entity.UsuarioRol;
@Repository
public interface UsuarioRolRepository extends JpaRepository<UsuarioRol, Long>{

}
