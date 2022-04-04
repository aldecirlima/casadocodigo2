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

	public List<Livro> ultimosLancamentos() {
		String jpql = "SELECT l FROM Livro l ORDER BY l.id DESC";
		return manager.createQuery(jpql, Livro.class).setMaxResults(4).getResultList();
	}

	public List<Livro> demaisLivros() {
		String jpql = "SELECT l FROM Livro l ORDER BY l.id DESC";
		return manager.createQuery(jpql, Livro.class).setFirstResult(4).getResultList();
	}

	public Livro buscarPorId(Integer id) {
		String jpql = "SELECT l FROM Livro l JOIN FETCH l.autores"
				+ " WHERE l.id = :id";
		return  manager.createQuery(jpql, Livro.class)
				.setParameter("id", id)
				.getSingleResult();
	}

}