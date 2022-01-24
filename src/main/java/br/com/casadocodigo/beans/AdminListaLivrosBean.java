package br.com.casadocodigo.beans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.casadocodigo.dao.LivroDao;
import br.com.casadocodigo.models.Livro;

/*
 * A anotattion @Model já emgloba as anotations @Named e @RequestScoped.
 */
@Model
public class AdminListaLivrosBean {

	@Inject
	private LivroDao dao;

	private List<Livro> livros = new ArrayList<>();

	public List<Livro> getLivros() {
		this.livros = dao.listar();
		return this.livros;
	}

}
