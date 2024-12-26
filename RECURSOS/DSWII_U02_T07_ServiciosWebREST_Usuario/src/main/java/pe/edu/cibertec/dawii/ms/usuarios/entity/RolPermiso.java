package pe.edu.cibertec.dawii.ms.usuarios.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Entity
@Table(name = "rol_permiso")
public class RolPermiso {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @ManyToOne
	    @JoinColumn(name = "id_rol", nullable = false)
	    private Rol rol;

	    @ManyToOne
	    @JoinColumn(name = "id_permiso", nullable = false)
	    private Permiso permiso;

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
