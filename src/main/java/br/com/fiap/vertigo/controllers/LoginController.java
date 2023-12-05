package br.com.fiap.vertigo.controllers;

import br.com.fiap.vertigo.dto.LoginDTO;
import br.com.fiap.vertigo.model.Usuario;
import br.com.fiap.vertigo.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoginController {

    private final UsuarioService usuarioService;

    public LoginController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<?> autenticarUsuario(@RequestBody LoginDTO loginDTO) {
        Optional<Usuario> usuario = usuarioService.validarLogin(loginDTO);

        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.status(401).body("Falha na autenticação: credenciais inválidas.");
        }
    }
}