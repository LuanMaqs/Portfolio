package com.portfolio.Controller;

import com.portfolio.Model.entities.Usuario;
import com.portfolio.Model.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.Optional;

@RestController
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping(value = "/login")
    public ResponseEntity loginUsuario(@RequestBody Usuario usuarioRequest){

//        EXEMPLO REQUEST USUARIO
//        {
//            "username": "teste@gmail.com",
//                "senha": "exemplo123"
//        }

        try {

            Optional<Usuario> usuario = usuarioRepository.findByEmailOrUsername(usuarioRequest.getUsuario(), usuarioRequest.getUsuario());


            HttpHeaders headers = new HttpHeaders();
            headers.add("Access-Control-Allow-Origin", "*");


            if(usuario.isPresent()){

                if(usuario.get().equals(usuarioRequest)){
                    return ResponseEntity.ok().headers(headers).build();
                }else{
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(headers).build();
                }

            }else{
                return ResponseEntity.notFound().headers(headers).build();
            }

        }catch (Exception e){
            throw e;
        }


    }

}