package pe.edu.cibertec.dawii.ms.usuarios.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.dawii.ms.usuarios.entity.Rol;
import pe.edu.cibertec.dawii.ms.usuarios.repository.RolRepository;
import pe.edu.cibertec.dawii.ms.usuarios.service.RolService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RolServiceImpl implements RolService {

    @Autowired
    RolRepository rolRepository;

    @Override
    public List<Rol> findAll() {
        return rolRepository.findAll();
    }

    @Override
    public Optional<Rol> findById(Long id) {
        return rolRepository.findById(id);
    }


    @Override
    public Rol save(Rol rol) {
        return rolRepository.save(rol);
    }

    @Override
    public void delete(Long id) {
        rolRepository.deleteById(id);
    }

    @Override
    public Optional<Rol> actualizarRol(Rol rol, Long id) {
        Optional<Rol> rolExistente = rolRepository.findById(id);

        return rolExistente.map(r -> {
            r.setNombre(rol.getNombre());
            r.setDescripcion(rol.getDescripcion());
            r.setFechaActualizacion(LocalDateTime.now());
            return rolRepository.save(r);
        });
    }


}
