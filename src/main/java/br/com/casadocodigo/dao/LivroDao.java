package br.com.casadocodigo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.casadocodigo.models.Livro;

public class LivroDao {

//	A anotação @PersistenceContext injeta automaticamente uma instância do EntityManager
	@PersistenceContext
	private EntityManager manager;

	public void salvar(Livro livro) {
		manager.persist(livro);
	}

	public List<Livro> listar() {

		String jpql = "SELECT DISTINCT(l) FROM Livro l" + " join fetch l.autores";

		return manager.createQuery(jpql, Livro.class).getResultList();
	}

}