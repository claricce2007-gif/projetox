package br.com.controleacesso.service;

import br.com.controleacesso.model.Usuario;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    private final List<Usuario> usuarios = new ArrayList<>();
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public void cadastrar(String nome, String senha) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome é obrigatório.");
        }
        if (senha == null || senha.isBlank()) {
            throw new IllegalArgumentException("A senha é obrigatória.");
        }

        String senhaHash = passwordEncoder.encode(senha);
        usuarios.add(new Usuario(nome.trim(), senhaHash));
    }

    public boolean verificarAcesso(String senhaDigitada) {
        if (senhaDigitada == null || senhaDigitada.isBlank()) {
            return false;
        }

        for (Usuario usuario : usuarios) {
            if (passwordEncoder.matches(
                    senhaDigitada, usuario.getSenhaHash())) {
                return true;
            }
        }
        return false;
    }

    public List<String> listarUsuarios() {
        List<String> nomes = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            nomes.add(usuario.getNome());
        }
        return nomes;
    }
}
