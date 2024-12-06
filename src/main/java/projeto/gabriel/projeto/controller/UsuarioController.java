package projeto.gabriel.projeto.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public Usuario criarUsuario(@RequestBody Usuario usuario){
        return repository.save(usuario);
    }

    @PutMapping("/{id}")
    public Usuario atualizarUsuario(@PathVariable Integer id, @RequestBody Usuario usuario) {
        final Usuario findUsuario = repository.findById(id).get();
        if(findUsuario == null){
            return null;
        }

        usuario.setId(findUsuario.getId());
        repository.save(usuario);

        return usuario;
    }

    @DeleteMapping("/{id}")
    public void deletarUsuario(@PathVariable Integer id){
        repository.deleteById(id);
    }

    @GetMapping
    public ArrayList<Usuario> listarUsuarios() {
        return (ArrayList<Usuario>) repository.findAll();
    }

    @GetMapping("/{id}")
    public Usuario buscarUsuario(@PathVariable Integer id) {
        return repository.findById(id).get();
    }
}
