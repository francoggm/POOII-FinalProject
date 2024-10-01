package projeto.gabriel.projeto.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import projeto.gabriel.projeto.model.Usuario;

public class UsuarioController {
  @PostMapping("/usuario")
    public Usuario criarUsuario(Usuario u){
        u.create();
        return u;
    }

    @PutMapping("/usuario")
    public Usuario atualizarUsuario(Usuario u) {
        u.update();
        return u;
    }

    @DeleteMapping("/usuario")
    public void deletarUsuario(Usuario u){
        u.delete();
    }

    @GetMapping("/usuario")
    public ArrayList<Usuario> listarUsuarios() {
        return Usuario.getAll();
    }

    @GetMapping("/usuario/{id}")
    public Usuario buscarUsuario(@PathVariable int id) {
        return Usuario.get(id);
    }
}
