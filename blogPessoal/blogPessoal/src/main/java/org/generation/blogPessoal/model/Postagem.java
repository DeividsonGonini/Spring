package org.generation.blogPessoal.model;

//importado para utilizarmos variaveis do tipo data.
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



//Anotações

//Essa Classe será uma entidade do JPA Hibernate
@Entity

//Essa entidade ira virar uma tabela no Banco de dados (Passr o nome da tabela que sera Criada)
@Table(name = "Postagem")


public class Postagem {
	//Identifica que será o Id
	@Id
	//Comportamento do Id transformando o atributo abaixo da notação em Primary Key e autoIncrement
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	//define tamanho minimo e/ou maximo do campo
	@NotNull//Não permite nulo
	@Size(min = 5, max = 100)
	private String titulo;

	@NotNull
	@Size(min = 5, max = 100)
	private String texto;
	
	//Capturando a Data, Hora, Minuto, Segundo e Milesimo assim que passar por essa classe (ao inserir o cadastro)
	//Temporal define que estamos trabalhando com Tempo
	@Temporal(TemporalType.TIMESTAMP)
	private Date date = new java.sql.Date(System.currentTimeMillis());
	
	
	//Getters and Setters
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	//Fim Getters and Setters
	
		
}
