package br.com.springboot.treinamento.controllers;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.treinamento.model.Usuario;
import br.com.springboot.treinamento.repository.UsuarioRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {
    
	@Autowired //Injeção de dependencia
	private UsuarioRepository usuarioRepository;
    
    @GetMapping(value= "listatodos")
    @ResponseBody //Retorna os dados para o corpo da resposta
    public ResponseEntity<List<Usuario>> listaUsuario(){ //Metodo de API
    	List<Usuario> usuarios = this.usuarioRepository.findAll(); //Percorre o banco listando os usuarios
    	
    	return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK); //retorna um JSON
    }
    
    @PostMapping(value= "salvar") //Mapeia a url
    @ResponseBody //Faz a descrição da respostas
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario){ //Recebe os dados para salvar
    	
    	Usuario user= this.usuarioRepository.save(usuario);
    	
    	return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
    }
    
    @DeleteMapping(value= "delete") //Mapeia a url
    @ResponseBody //Faz a descrição da respostas
    public ResponseEntity<String> delete(@RequestParam long idUser){ //Recebe os dados para deletar
    	
    	this.usuarioRepository.deleteById(idUser);
    	
    	return new ResponseEntity<String>("Usuário Deletado com Sucesso", HttpStatus.OK);
    }
    
    @GetMapping(value= "buscaruserid") //Mapeia a url
    @ResponseBody //Faz a descrição da respostas
    public ResponseEntity<Usuario> buscaruserid(@RequestParam(name= "idUser") Long idUser){ //Recebe os dados para deletar
    	
    	Usuario user= this.usuarioRepository.findById(idUser).get();
    	
    	return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    }
    
    @PutMapping(value= "atualizar") //Mapeia a url
    @ResponseBody //Faz a descrição da respostas
    public ResponseEntity<?> atualizar(@RequestBody Usuario usuario){ //Recebe os dados para salvar
    	
    	if(Objects.isNull(usuario.getId())) {
    		return new ResponseEntity<String>("Id não foi informado", HttpStatus.OK);
    	}
    	
    	Usuario user= this.usuarioRepository.saveAndFlush(usuario);
    	
    	return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    }
    
    @GetMapping(value= "buscarPorNome") //Mapeia a url
    @ResponseBody //Faz a descrição da respostas
    public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam(name= "name") String name){ //Recebe os dados para deletar
    	
    	List<Usuario> usuarios= this.usuarioRepository.buscarPorNome(name.trim().toUpperCase());
    	
    	return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
    }
    
}
