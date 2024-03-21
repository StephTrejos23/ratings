package io.javabrains.ratingsdataservice.controller.security;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/module") //endpoint es una url que permite al cliente acceder a un recurso, la url tiene que tener un nomnre en request mapping
public class ModuleController {

    @GetMapping("/holaMundo") //para obtener informacion de nuestro recurso
    public String holaMundo(){
        return "Hola Mundo";
    }

    @PostMapping("/chaoMundo") //para insertar informacion
    public String chaoMundo(){
        return "Chao Mundo";
    }

    @GetMapping("/mundo") //para obtener informacion de nuestro recurso
    public String mundo(){
        return "Mundo";
    }
}

//http://localhost:8083/module/holaMundo
//http, luego nombre servidor, puerto, el nombre del request mapping y por ultimo el nombre del metodo get mapping
