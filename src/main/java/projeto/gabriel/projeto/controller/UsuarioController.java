package projeto.gabriel.projeto.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import projeto.gabriel.projeto.model.Usuario;
import projeto.gabriel.projeto.repository.UsuarioRepository;

@RestController
@CrossOrigin(origins = "*")
@AllArgsConstructor
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioRepository repository;

    @PostMapping
    public Usuario criarUsuario(Usuario usuario){
        usuario.setId(null);
        return repository.save(usuario);
    }

    @PutMapping
    public Usuario atualizarUsuario(Usuario usuario) {
        if(usuario.getId() == null){
            return null;
        }
        
        return repository.save(usuario);
    }

    @DeleteMapping("/{id}")
    public void deletarUsuario(Usuario usuario){
        repository.delete(usuario);
    }

    @GetMapping
    public ArrayList<Usuario> listarUsuarios() {
        return (ArrayList<Usuario>) repository.findAll();
    }

    @GetMapping("/{id}")
    public Usuario buscarUsuario(@PathVariable long id) {
        return repository.findById(id).get();
    }
}
