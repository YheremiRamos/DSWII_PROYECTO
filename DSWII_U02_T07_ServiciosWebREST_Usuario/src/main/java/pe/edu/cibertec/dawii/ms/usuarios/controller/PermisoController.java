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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.cibertec.dawii.ms.usuarios.entity.Permiso;
import pe.edu.cibertec.dawii.ms.usuarios.service.PermisoService;

@RestController
@RequestMapping("/api/permisos")
public class PermisoController {

    @Autowired
    private PermisoService permisoService;

    @GetMapping
    public ResponseEntity<List<Permiso>> listarTodos() {
        List<Permiso> permisos = permisoService.listarTodosPermisos();
        return ResponseEntity.ok(permisos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Permiso> obtenerPorId(@PathVariable Long id) {
        Permiso permiso = permisoService.obtenerPorIdPermiso(id);
        if (permiso != null) {
            return ResponseEntity.ok(permiso);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<Permiso> crear(@RequestBody Permiso permiso) {
    	  permiso.setFechaCreacion(LocalDateTime.now());
          permiso.setFechaActualizacion(LocalDateTime.now());
    	Permiso nuevoPermiso = permisoService.crearPermiso(permiso);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPermiso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> actualizar(@PathVariable Long id, @RequestBody Permiso permiso) {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<Permiso> permisoActualizado = permisoService.actualizar(id, permiso);

            // Si se actualizó correctamente el permiso
            if (permisoActualizado != null) {
                response.put("mensaje", "Permiso actualizado exitosamente");
                response.put("permiso", permisoActualizado);
                permiso.setFechaActualizacion(LocalDateTime.now());
                return ResponseEntity.ok(response);  // Devuelve 200 OK
            } else {
                response.put("error", "Permiso no encontrado");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);  // Devuelve 404 si no se encuentra
            }
        } catch (Exception e) {
            response.put("error", "Error al actualizar el permiso");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);  // Devuelve 500 si ocurre un error
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        permisoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
  //_________________________ OBTENCIÓN DE DATOS EN DISTINTOS FORMATOS ________________________
  	@GetMapping(value ="/xml", produces = MediaType.APPLICATION_XML_VALUE)
  	public List<Permiso> listaPermisosTodosXML() {
  		return permisoService.listarTodosPermisos();
  	} 
  	
  	@GetMapping(value ="/json", produces = MediaType.APPLICATION_JSON_VALUE)
  	public List<Permiso> listaPermisosTodosJSON() {
  		return permisoService.listarTodosPermisos();
  	} 
  	
  	@GetMapping(value = "/html", produces = MediaType.TEXT_HTML_VALUE)
  	public String listaPermisosTodosHTML() {
  	    List<Permiso> lista = permisoService.listarTodosPermisos();
  	    
  	   StringBuilder html = new StringBuilder();
  	    html.append("""
  	            <html>
  	                <head>
  	                    <title>Lista de Permisos</title>
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
  	                        <h1>Gestión de Permisos</h1>
  	                    </nav>
  	                    <div class="container">
  	                        <h1>Lista de Permisos</h1>
  	                        <table>
  	                            <tr>
  	                                <th>ID</th>
  	                                <th>Nombre</th>
  	                                <th>Descripción</th>
  	                                <th>Fecha de Creación</th>
  	                                <th>Fecha de Actualización</th>
  	                            </tr>
  	            """);

  	    for (Permiso permiso : lista) {
  	        html.append("<tr>")
  	            .append("<td>").append(permiso.getId()).append("</td>")
  	            .append("<td>").append(permiso.getNombre()).append("</td>")
  	            .append("<td>").append(permiso.getDescripcion()).append("</td>")
  	            .append("<td>").append(permiso.getFechaCreacion()).append("</td>")
  	            .append("<td>").append(permiso.getFechaActualizacion()).append("</td>")
  	            .append("</tr>");
  	    }

  	    html.append("""
  	                        </table>
  	                        <p>Regresa a la <a href="/api/permisos/json"> Lista JSON</a></p>        
  	                        <p>Regresa a la <a href="/api/permisos/xml">Lista XML</a></p>

  	                    </div>
  	                </body>
  	            </html>
  	            """);

  	    return html.toString();
  	}
}