package br.com.controleacesso.controller;

import br.com.controleacesso.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public record CadastroRequest(String nome, String senha) {}
    public record AcessoRequest(String senha) {}
    public record Resposta(String mensagem) {}

    @PostMapping("/usuarios")
    public ResponseEntity<Resposta> cadastrar(
            @RequestBody CadastroRequest dados) {
        try {
            usuarioService.cadastrar(dados.nome(), dados.senha());
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new Resposta("Usuário cadastrado com sucesso."));
        } catch (IllegalArgumentException erro) {
            return ResponseEntity.badRequest()
                    .body(new Resposta(erro.getMessage()));
        }
    }

    @PostMapping("/acesso")
    public ResponseEntity<Resposta> verificarAcesso(
            @RequestBody AcessoRequest dados) {
        boolean permitido = dados != null
                && usuarioService.verificarAcesso(dados.senha());

        if (permitido) {
            return ResponseEntity.ok(new Resposta("Acesso permitido."));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new Resposta("Acesso negado."));
    }

    @GetMapping("/usuarios")
    public List<String> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }
}
