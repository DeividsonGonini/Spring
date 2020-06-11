package org.generation.blogPessoal.repository;

//importando a List da java Util
import java.util.List;
//importando a model
import org.generation.blogPessoal.model.Postagem;
//importando a classe JPA repository
import org.springframework.data.jpa.repository.JpaRepository;
//importando o repositorio
import org.springframework.stereotype.Repository;




//importando o repository
@Repository
//extender a classe JPA repository passar o tipo de entidade (Model) e o tipo do ID no tipo primitivo  
public interface PostagemRepository extends JpaRepository<Postagem, Long> {
	
	//construindo uma Query (medoto que nao temos no JPA nativo)
	
	//Query pra listar todas as postagems filtrando pelo titulo (o parametro é o mesmo atribulto da Model pelo qual deseja listar)
	//List trara o array (lista) de objetos (postagem)
	//Containing = trara tudo o contem os caracteres que tera na variavel (igual o Like do DB) 
	//IgnoreCase desconsidera maiuscula e minuscula (converte tudo para minusculo e faz a busca)
	public List<Postagem> findAllByTituloContainingIgnoreCase (String titulo);

}
