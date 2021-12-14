package br.com.springboot.treinamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.springboot.treinamento.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	@Query(value= "SELECT u FROM Usuario u WHERE upper(trim(u.nome)) LIKE %?1%")
	public List<Usuario> buscarPorNome(String name);
}
