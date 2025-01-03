package pe.edu.cibertec.dawii.ms.usuarios.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import pe.edu.cibertec.dawii.ms.usuarios.entity.Usuario;
import pe.edu.cibertec.dawii.ms.usuarios.entity.UsuarioRol;
import pe.edu.cibertec.dawii.ms.usuarios.service.UsuarioRolService;

@RestController
@RequestMapping("/api/usuarioRol")
@RequiredArgsConstructor
public class UsuarioRolController {
	   @Autowired
	    private UsuarioRolService usuarioRolService;

	    // Listar todos los UsuarioRol
	    @GetMapping
	    public ResponseEntity<List<UsuarioRol>> listarUsuarioRoles() {
	        List<UsuarioRol> usuarioRoles = usuarioRolService.findAll();
	        return new ResponseEntity<>(usuarioRoles, HttpStatus.OK);
	    }

	    // Obtener UsuarioRol por ID
	    @GetMapping("/{id}")
	    public ResponseEntity<UsuarioRol> obtenerUsuarioRolPorId(@PathVariable long id) {
	        UsuarioRol usuarioRol = usuarioRolService.obtenerPorIdUsuarioRol(id);
	        if (usuarioRol != null) {
	            return new ResponseEntity<>(usuarioRol, HttpStatus.OK);
	        } else 	{
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }

	    // Crear un nuevo UsuarioRol
	    @PostMapping
	    public ResponseEntity<UsuarioRol> crearUsuarioRol(@RequestBody UsuarioRol usuarioRol) {
	        UsuarioRol nuevoUsuarioRol = usuarioRolService.save(usuarioRol);
	        return new ResponseEntity<>(nuevoUsuarioRol, HttpStatus.CREATED);
	    }

	    // Actualizar un UsuarioRol existente
	    @PutMapping("/{id}")
	    public ResponseEntity<UsuarioRol> actualizarUsuarioRol(@PathVariable long id, @RequestBody UsuarioRol usuarioRol) {
	        Optional<UsuarioRol> usuarioRolActualizado = usuarioRolService.actualizarUsuarioRol(usuarioRol, id);
	        usuarioRol.setFechaActualizacion(LocalDateTime.now());
	        return usuarioRolActualizado
	                .map(ur -> new ResponseEntity<>(ur, HttpStatus.OK))
	                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	    }

	    // Eliminar un UsuarioRol
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> eliminarUsuarioRol(@PathVariable long id) {
	        usuarioRolService.delete(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
	    //_________________________ OBTENCIÓN DE DATOS EN DISTINTOS FORMATOS ________________________
	  	@GetMapping(value ="/xml", produces = MediaType.APPLICATION_XML_VALUE)
	  	public List<UsuarioRol> listaUsuarioRolTodosXML() {
	  		return usuarioRolService.findAll();
	  	} 
	  	
	  	@GetMapping(value ="/json", produces = MediaType.APPLICATION_JSON_VALUE)
	  	public List<UsuarioRol> listaUsuarioRolTodosJson() {
	  		return usuarioRolService.findAll();
	  	} 
	  	
	  	@GetMapping(value = "/html", produces = MediaType.TEXT_HTML_VALUE)
	  	public String listaUsuarioRolTodosHTML() {
	  	    List<UsuarioRol> lista = usuarioRolService.findAll();
	  	    
	  	   StringBuilder html = new StringBuilder();
	  	    html.append("""
	  	            <html>
	  	                <head>
	  	                    <title>Lista de Usuarios y roles</title>
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
	  	                   +         background-color: #007bff;
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
	  	                        <h1>Gestión de usuarios y roles</h1>
	  	                    </nav>
	  	                    <div class="container">
	  	                        <h1>Lista de usuarios y roles</h1>
	  	                        <table>
	  	                            <tr>
	  	                                <th>Id</th>
	  	                                <th>Id_Usuario</th>
	  	                                <th>Id_Rol</th>
	  	                                <th>Fecha de Creación</th>
	  	                                <th>Fecha de Actualización</th>
	  	                            </tr>
	  	            """);

	  	    for (UsuarioRol usuarioRol : lista) {
	  	        html.append("<tr>")
	  	            .append("<td>").append(usuarioRol.getId()).append("</td>")
	  	            .append("<td>").append(usuarioRol.getUsuario().getId()).append("</td>")
	  	            .append("<td>").append(usuarioRol.getRol().getId()).append("</td>")
	  	            .append("<td>").append(usuarioRol.getFechaCreacion()).append("</td>")
	  	            .append("<td>").append(usuarioRol.getFechaActualizacion()).append("</td>")
	  	            .append("</tr>");
	  	    }

	  	    html.append("""
	  	                        </table>
	  	                        <p>Regresa a la <a href="/api/usuarioRol/json"> Lista JSON</a></p>        
	  	                        <p>Regresa a la <a href="/api/usuarioRol/xml">Lista XML</a></p>

	  	                    </div>
	  	                </body>
	  	            </html>
	  	            """);

	  	    return html.toString();
	  	}

	}

