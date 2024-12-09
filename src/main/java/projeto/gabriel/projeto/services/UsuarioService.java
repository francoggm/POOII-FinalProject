package projeto.gabriel.projeto.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.AllArgsConstructor;
import projeto.gabriel.projeto.model.Usuario;
import projeto.gabriel.projeto.repository.UsuarioRepository;

@Service
@AllArgsConstructor
public class UsuarioService {
  
  private final UsuarioRepository repository;

    public Usuario criarUsuario(@RequestBody Usuario usuario){
        return repository.save(usuario);
    }

    public Usuario atualizarUsuario(@PathVariable Integer id, @RequestBody Usuario usuario) {
        final Usuario findUsuario = repository.findById(id).get();
        if(findUsuario == null){
            return null;
        }

        usuario.setId(findUsuario.getId());
        repository.save(usuario);

        return usuario;
    }

    public void deletarUsuario(@PathVariable Integer id){
        repository.deleteById(id);
    }

    public ArrayList<Usuario> listarUsuarios() {
        return (ArrayList<Usuario>) repository.findAll();
    }

    public Usuario buscarUsuario(@PathVariable Integer id) {
        return repository.findById(id).get();
    }
}
