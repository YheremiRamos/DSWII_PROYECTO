package pe.edu.cibertec.dawii.ms.usuarios.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.cibertec.dawii.ms.usuarios.entity.Rol;
import pe.edu.cibertec.dawii.ms.usuarios.entity.RolPermiso;
import pe.edu.cibertec.dawii.ms.usuarios.repository.RolPermisoRepository;
import pe.edu.cibertec.dawii.ms.usuarios.repository.RolRepository;
import pe.edu.cibertec.dawii.ms.usuarios.service.RolPermisoService;

@RestController
@RequestMapping("/api/rolPermisos")
public class RolPermisoController {
	
	   @Autowired
	    private RolPermisoService rolPermisoService;
    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private RolPermisoRepository rolPermisoRepository;

	// Listar todos los RolPermiso
	    @GetMapping
	    public ResponseEntity<List<RolPermiso>> listarRolPermisos() {
	        List<RolPermiso> rolPermisos = rolPermisoService.listarRolPermisos();
	        return new ResponseEntity<>(rolPermisos, HttpStatus.OK);
	    }

	    // Obtener RolPermiso por ID
	    @GetMapping("/{id}")
	    public ResponseEntity<RolPermiso> obtenerPorIdRolPermiso(@PathVariable Long id) {
	        RolPermiso rolPermiso = rolPermisoService.obtenerPorIdRolPermiso(id);
	        if (rolPermiso != null) {
	            return new ResponseEntity<>(rolPermiso, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }

	    // Crear un nuevo RolPermiso
	    @PostMapping
	    public ResponseEntity<RolPermiso> crearRolPermiso(@RequestBody RolPermiso datos) {
			datos.setFechaActualizacion(LocalDateTime.now());
			datos.setFechaCreacion(LocalDateTime.now());
	      	return  ResponseEntity.ok(rolPermisoRepository.save(datos));

	    }
	    // Actualizar un RolPermiso existente usando idRol e idPermiso
	    @PutMapping("/{id}")
	    public ResponseEntity<Map<String, Object>> actualizarRolPermiso(@PathVariable Long id, @RequestBody RolPermiso rolPermiso) {

			Map<String, Object> response = new HashMap<>();

			try{
				Optional<RolPermiso> rolPermisoActualizado = rolPermisoService.actualizar(rolPermiso,id);

				return rolPermisoActualizado.map(r -> {
					response.put("mensaje", "RolPermiso actualizado con exito");
					response.put("rolPermiso", r);
					rolPermiso.setFechaActualizacion(LocalDateTime.now());
					return ResponseEntity.ok(response);
				}).orElseGet(() -> {
					response.put(("error"), "RolPermiso no encontrado");
					return ResponseEntity.notFound().build();
				});


			}catch (Exception e) {
				response.put("mensaje", e.getMessage());
				rolPermiso.setFechaActualizacion(LocalDateTime.now());
				return ResponseEntity.status(500).body(response);
			}

		}

	    // Eliminar un RolPermiso
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> eliminarRolPermiso(@PathVariable long id) {
	        rolPermisoService.eliminarRolPermiso(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
	    
	    //_________________________ OBTENCIÓN DE DATOS EN DISTINTOS FORMATOS ________________________
	  	@GetMapping(value ="/xml", produces = MediaType.APPLICATION_XML_VALUE)
	  	public List<RolPermiso> listaRolPermisoTodosXML() {
	  		return rolPermisoService.listarRolPermisos();
	  	} 
	  	
	  	@GetMapping(value ="/json", produces = MediaType.APPLICATION_JSON_VALUE)
	  	public List<RolPermiso> listaRolPermisoTodosJson() {
	  		return rolPermisoService.listarRolPermisos();
	  	} 
	  	
	  	@GetMapping(value = "/html", produces = MediaType.TEXT_HTML_VALUE)
	  	public String listaRolPermisoTodosHTML() {
	  	    List<RolPermiso> lista = rolPermisoService.listarRolPermisos();
	  	    
	  	   StringBuilder html = new StringBuilder();
	  	    html.append("""
	  	            <html>
	  	                <head>
	  	                    <title>Lista de roles y Permisos</title>
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
	  	                        <h1>Gestión de Roles y permisos</h1>
	  	                    </nav>
	  	                    <div class="container">
	  	                        <h1>Lista de roles con permisos</h1>
	  	                        <table>
	  	                            <tr>
	  	                                <th>Id_Rol_Permiso</th>
	  	                                <th>Id_rol</th>
	  	                                <th>Id_permiso</th>
	  	                                <th>Fecha de Creación</th>
	  	                                <th>Fecha de Actualización</th>
	  	                            </tr>
	  	            """);

	  	    for (RolPermiso rolPermiso : lista) {
	  	        html.append("<tr>")
	  	            .append("<td>").append(rolPermiso.getId()).append("</td>")
	  	            .append("<td>").append(rolPermiso.getRol().getId()).append("</td>")
	  	            .append("<td>").append(rolPermiso.getPermiso().getId()).append("</td>")
	  	            .append("<td>").append(rolPermiso.getFechaCreacion()).append("</td>")
	  	            .append("<td>").append(rolPermiso.getFechaActualizacion()).append("</td>")
	  	            .append("</tr>");
	  	    }

	  	    html.append("""
	  	                        </table>
	  	                        <p>Regresa a la <a href="/api/rolPermisos/json"> Lista JSON</a></p>        
	  	                        <p>Regresa a la <a href="/api/rolPermisos/xml">Lista XML</a></p>

	  	                    </div>
	  	                </body>
	  	            </html>
	  	            """);

	  	    return html.toString();
	  	}

	}

	