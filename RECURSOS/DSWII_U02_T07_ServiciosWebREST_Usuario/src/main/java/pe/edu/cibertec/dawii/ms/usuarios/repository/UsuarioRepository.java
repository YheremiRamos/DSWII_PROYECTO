package pe.edu.cibertec.dawii.ms.usuarios.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.dawii.ms.usuarios.entity.Usuario;

import java.util.Optional;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
        Optional<Usuario> findByEmail(String email);

}