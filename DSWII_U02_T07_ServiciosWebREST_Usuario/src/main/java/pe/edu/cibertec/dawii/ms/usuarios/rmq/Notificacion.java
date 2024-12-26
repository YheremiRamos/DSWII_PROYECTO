package pe.edu.cibertec.dawii.ms.usuarios.rmq;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notificacion implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String asunto;
	private String contenido;
	private  String email;
}
