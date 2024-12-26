    package pe.edu.cibertec.dawii.ms.usuarios.entity;

    import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Entity
@Table(name = "usuario")
    public class Usuario {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(name = "nombre_usuario", nullable = false, unique = true, length = 50)
	    private String nombreUsuario;

	    @Column(nullable = false, length = 255)
	    private String contrasena;

	    @Column(nullable = false, unique = true, length = 100)
	    private String email;

	    @Column(nullable = false)
	    private Boolean activo = true;

	   /* @JsonManagedReference

	    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
	    private Set<UsuarioRol> roles = new HashSet<>();
*/
	    @Column(name = "fecha_creacion", nullable = false, updatable = false)
	    private LocalDateTime fechaCreacion;

	    @Column(name = "fecha_actualizacion")
	    private LocalDateTime fechaActualizacion;

	    @PrePersist
	    public void prePersist() {
	        this.fechaCreacion = LocalDateTime.now();
	    }

	    @PreUpdate
	    public void preUpdate() {
	        this.fechaActualizacion = LocalDateTime.now();
	    }
	}