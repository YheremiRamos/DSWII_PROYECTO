package pe.edu.cibertec.dawii.ms.usuarios.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pe.edu.cibertec.dawii.ms.usuarios.entity.Rol;
import pe.edu.cibertec.dawii.ms.usuarios.service.RolService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RolController {

    @Autowired
     RolService rolService;



    @GetMapping
    public ResponseEntity<List<Rol>> getAllRoles() {
        return ResponseEntity.ok(rolService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Rol>> getRolById(@PathVariable Long id) {
        return ResponseEntity.ok(rolService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Rol> createRol(@RequestBody Rol rol) {
        rol.setFechaCreacion(LocalDateTime.now());
        rol.setFechaActualizacion(LocalDateTime.now());
        return ResponseEntity.ok(rolService.save(rol));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> actualizarRol(@PathVariable Long id, @RequestBody Rol rol) {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<Rol> rolActualizado = rolService.actualizarRol(rol, id); // Llamada al servicio para actualizar el rol

            // Verifica si el rol fue encontrado y actualizado
            return rolActualizado.map(r -> {
                        response.put("mensaje", "Rol actualizado exitosamente");
                        response.put("rol", r);
                        rol.setFechaActualizacion(LocalDateTime.now());
                        return ResponseEntity.ok(response);
                    })
                    .orElseGet(() -> {
                        response.put("error", "Rol no encontrado");
                        return ResponseEntity.notFound().build();
                    });

        } catch (Exception e) {
            response.put("error", "Error al actualizar rol");
            e.printStackTrace();
            rol.setFechaActualizacion(LocalDateTime.now());
            return ResponseEntity.status(500).body(response);  // Devuelve un 500 en caso de error
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRol(@PathVariable Long id) {
        rolService.delete(id);
        return ResponseEntity.noContent().build();
    }
    //_________________________ OBTENCIÓN DE DATOS EN DISTINTOS FORMATOS ________________________
  	@GetMapping(value ="/xml", produces = MediaType.APPLICATION_XML_VALUE)
  	public List<Rol> listaRolTodosXML() {
  		return rolService.findAll();
  	} 
  	
  	@GetMapping(value ="/json", produces = MediaType.APPLICATION_JSON_VALUE)
  	public List<Rol> listaRolTodosJSON() {
  		return rolService.findAll();
  	} 
  	
  	@GetMapping(value = "/html", produces = MediaType.TEXT_HTML_VALUE)
  	public String listaRolTodosHTML() {
  	    List<Rol> lista = rolService.findAll();
  	    
  	   StringBuilder html = new StringBuilder();
  	    html.append("""
  	            <html>
  	                <head>
  	                    <title>Lista de roles</title>
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
  	                        <h1>Gestión de Roles</h1>
  	                    </nav>
  	                    <div class="container">
  	                        <h1>Lista de roles</h1>
  	                        <table>
  	                            <tr>
  	                                <th>Id</th>
  	                                <th>Nombre</th>
  	                                <th>Descripción</th>
  	                                <th>Fecha de Creación</th>
  	                                <th>Fecha de Actualización</th>
  	                            </tr>
  	            """);

  	    for (Rol rol : lista) {
  	        html.append("<tr>")
  	            .append("<td>").append(rol.getId()).append("</td>")
  	            .append("<td>").append(rol.getNombre()).append("</td>")
  	            .append("<td>").append(rol.getDescripcion()).append("</td>")
  	            .append("<td>").append(rol.getFechaCreacion()).append("</td>")
  	            .append("<td>").append(rol.getFechaActualizacion()).append("</td>")
  	            .append("</tr>");
  	    }

  	    html.append("""
  	                        </table>
  	                        <p>Regresa a la <a href="/api/roles/json"> Lista JSON</a></p>        
  	                        <p>Regresa a la <a href="/api/roles/xml">Lista XML</a></p>

  	                    </div>
  	                </body>
  	            </html>
  	            """);

  	    return html.toString();
  	}

}
