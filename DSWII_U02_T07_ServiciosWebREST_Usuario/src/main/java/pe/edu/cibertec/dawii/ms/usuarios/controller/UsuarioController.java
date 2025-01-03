package pe.edu.cibertec.dawii.ms.usuarios.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.cibertec.dawii.ms.usuarios.entity.RolPermiso;
import pe.edu.cibertec.dawii.ms.usuarios.entity.Usuario;
import pe.edu.cibertec.dawii.ms.usuarios.repository.UsuarioRepository;
import pe.edu.cibertec.dawii.ms.usuarios.service.UsuarioService;
import pe.edu.cibertec.dawii.ms.usuarios.util.AppSettings;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class UsuarioController {
	@Autowired
	UsuarioService usuarioService;
    @Autowired
    private UsuarioRepository usuarioRepository;

	@GetMapping
	public List<Usuario> listarUsuarios(){
		return usuarioRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Long id){
		Optional<Usuario> usuario = usuarioService.obtenerUsuarioPorId(id);
		return usuario
				.map(ResponseEntity::ok)  // 200 en caso de encontrar
				.orElseGet(() -> ResponseEntity.notFound().build()); // 404 si no existe
	}

	@GetMapping("/filtros/email")
	public ResponseEntity<Usuario> obtenerUsuarioPorEmail(@RequestParam String email){
		Optional<Usuario> usuario = usuarioService.findByEmail(email);
		return usuario
				.map(ResponseEntity::ok)  // 200 en caso de encontrar
				.orElseGet(() -> ResponseEntity.notFound().build()); // 404 si no existe
	}
	
	@PostMapping
	public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {

		try {
			// Cifrado de la contraseña
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String passwordCifrada = passwordEncoder.encode(usuario.getContrasena());
			usuario.setContrasena(passwordCifrada);
			usuario.setFechaActualizacion(LocalDateTime.now());


			// Guardar el usuario
			Usuario nuevoUsuario = usuarioService.crearUsuario(usuario);
			return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}


	@PutMapping("/{id}")
	public ResponseEntity<Map<String, Object>> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
		Map<String, Object> response = new HashMap<>();
		try {
			// Llamada al servicio para actualizar el usuario
			Optional<Usuario> usuarioActualizado = usuarioService.actualizarUsuario(usuario,id);

			// Verifica si el usuario fue encontrado y actualizado
			return usuarioActualizado
					.map(u -> {
						response.put("mensaje", "Usuario actualizado exitosamente");
						response.put("usuario", u);
						usuario.setFechaActualizacion(LocalDateTime.now());
						return ResponseEntity.ok(response);  // Devuelve un 200 OK si se encontró y actualizó
					})
					.orElseGet(() -> {
						response.put("error", "Usuario no encontrado");
						return ResponseEntity.notFound().build();  // Devuelve un 404 si el usuario no existe
					});

		} catch (Exception e) {
			response.put("error", "Error al actualizar usuario");
			e.printStackTrace();
			return ResponseEntity.status(500).body(response);  // Devuelve un 500 en caso de error
		}
	}



	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
		usuarioService.eliminarUsuario(id);
		return ResponseEntity.noContent().build();
	}
	 //_________________________ OBTENCIÓN DE DATOS EN DISTINTOS FORMATOS ________________________
  	@GetMapping(value ="/xml", produces = MediaType.APPLICATION_XML_VALUE)
  	public List<Usuario> listaUsuarioTodosXML() {
  		return usuarioService.obtenerTodosUsuarios();
  	} 
  	
  	@GetMapping(value ="/json", produces = MediaType.APPLICATION_JSON_VALUE)
  	public List<Usuario> listaUsuarioTodosJson() {
  		return usuarioService.obtenerTodosUsuarios();
  	} 
  	
  	@GetMapping(value = "/html", produces = MediaType.TEXT_HTML_VALUE)
  	public String listaUsuarioTodosHTML() {
  	    List<Usuario> lista = usuarioService.obtenerTodosUsuarios();
  	    
  	   StringBuilder html = new StringBuilder();
  	    html.append("""
  	            <html>
  	                <head>
  	                    <title>Lista de Usuarios</title>
  	                    <style>
  	                        body {
  	                            font-family: Arial, sans-serif;
  	                            margin: 0;
  	                            padding: 0;
  	                            background-color: #f8f9fa;
  	                        }
  	                        nav {
  	                            background-color: #007bff;
  	                            padding: 10px 20px;
  	                        }
  	                        nav h1 {
  	                            color: white;
  	                            margin: 0;
  	                        }
  	                        .container {
  	                            padding: 20px;
  	                        }
  	                        h1 {
  	                            text-align: center;
  	                            color: #333;
  	                        }
  	                        table {
  	                            width: 100%;
  	                            border-collapse: collapse;
  	                            margin: 20px 0;
  	                            background: white;
  	                            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  	                            border-radius: 8px;
  	                        }
  	                        th, td {
  	                            padding: 12px 15px;
  	                            text-align: left;
  	                            border-bottom: 1px solid #ddd;
  	                        }
  	                        th {
  	                            background-color: #007bff;
  	                            color: white;
  	                        }
  	                        tr:hover {
  	                            background-color: #f1f1f1;
  	                        }
  	                        tr:last-child td {
  	                            border-bottom: none;
  	                        }
  	                        a {
  	                            text-decoration: none;
  	                            color: #007bff;
  	                        }
  	                        a:hover {
  	                            text-decoration: underline;
  	                        }
  	                    </style>
  	                </head>
  	                <body>
  	                    <nav>
  	                        <h1>Gestión de usuarios</h1>
  	                    </nav>
  	                    <div class="container">
  	                        <h1>Lista de usuarios</h1>
  	                        <table>
  	                            <tr>
  	                                <th>Id</th>
  	                                <th>Nombre</th>
  	                                <th>Contraseña</th>
  	                                <th>Email</th>
  	                                <th>Activo</th>
  	                                <th>Fecha de Creación</th>
  	                                <th>Fecha de Actualización</th>
  	                            </tr>
  	            """);

  	    for (Usuario usuario : lista) {
  	        html.append("<tr>")
  	            .append("<td>").append(usuario.getId()).append("</td>")
  	            .append("<td>").append(usuario.getNombreUsuario()).append("</td>")
  	            .append("<td>").append(usuario.getContrasena()).append("</td>")
  	            .append("<td>").append(usuario.getEmail()).append("</td>")
	            .append("<td>").append(usuario.getActivo()).append("</td>")
  	            .append("<td>").append(usuario.getFechaCreacion()).append("</td>")
  	            .append("<td>").append(usuario.getFechaActualizacion()).append("</td>")
  	            .append("</tr>");
  	    }

  	    html.append("""
  	                        </table>
  	                        <p>Regresa a la <a href="/api/usuario/json"> Lista JSON</a></p>        
  	                        <p>Regresa a la <a href="/api/usuario/xml">Lista XML</a></p>

  	                    </div>
  	                </body>
  	            </html>
  	            """);

  	    return html.toString();
  	}

}

	