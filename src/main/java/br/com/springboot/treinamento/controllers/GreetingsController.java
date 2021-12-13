package br.com.springboot.treinamento.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
    @RequestMapping(value = "/mostrarnome/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name) {
        return "Hello " + name + "!";
    }
    
    @RequestMapping(value= "/olamundo/{nome}", method= RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String retornaOlaMundo(@PathVariable String nome) {
    	
    	Usuario usuario = new Usuario();
    	usuario.setNome(nome);
    	this.usuarioRepository.save(usuario); //Grava no banco
    	
    	return "Olá mundo "+nome;
    }
    
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
    
}
