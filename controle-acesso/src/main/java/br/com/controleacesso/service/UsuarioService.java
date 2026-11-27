
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

    // Cadastrar um novo usuário
    public void cadastrar(String nome, String senha) {

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome é obrigatório.");
        }

        if (senha == null || senha.isBlank()) {
            throw new IllegalArgumentException("A senha é obrigatória.");
        }

        String nomeLimpo = nome.trim();

        String senhaHash = passwordEncoder.encode(senha);

        usuarios.add(new Usuario(nomeLimpo, senhaHash));
    }

    // Verificar nome e senha do usuário
    public boolean verificarAcesso(String nome, String senhaDigitada) {

        if (nome == null || nome.isBlank()
                || senhaDigitada == null || senhaDigitada.isBlank()) {
            return false;
        }

        for (Usuario usuario : usuarios) {

            if (usuario.getNome().equalsIgnoreCase(nome.trim())
                    && passwordEncoder.matches(
                    senhaDigitada, usuario.getSenhaHash())) {

                return true;
            }
        }

        return false;
    }

    // Listar os usuários cadastrados
    public List<String> listarUsuarios() {

        List<String> nomes = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            nomes.add(usuario.getNome());
        }

        return nomes;
    }
}
