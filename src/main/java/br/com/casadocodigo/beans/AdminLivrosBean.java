package br.com.casadocodigo.beans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import br.com.casadocodigo.dao.AutorDao;
import br.com.casadocodigo.dao.LivroDao;
import br.com.casadocodigo.models.Autor;
import br.com.casadocodigo.models.Livro;

// CDI

// A anotação @Named indica que o CDI passa a gerenciar a comunicação entre a classe e a tela do navegador.
// por padrão o nome da classe no CDI é o nome completo com a primeira letra minúscula
// podemos alterar este valor default informando o parâmetro value na classe

@Named(value = "adminLivrosBean")
@RequestScoped
public class AdminLivrosBean {

	private Livro livro = new Livro();

//	Context and Dependency Injection - CDI
	@Inject
	private LivroDao dao;

	@Inject
	private AutorDao autorDao;

//	O inject do FacesContext só foi possível porque foi criada a classe FacesContextProducer
	@Inject
	private FacesContext context;

	private List<Integer> autoresId = new ArrayList<>();
	


	@Transactional
	public String salvar() {
		for (Integer autorId : autoresId) {
			livro.getAutores().add(new Autor(autorId));
		}
		dao.salvar(livro);
//		Obtendo escopo de Flash para que a mensagem seja mantida até a próxima requisição após o redirect
		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage(null, new FacesMessage("Livro cadastrado com sucesso!"));

		// Ao retornar uma string o servidor redireciona para a página indicada na
		// string com o parametro faces-redirect=true.
		return "/livros/lista?faces-redirect=true";

	}

	public List<Autor> getAutores() {
		return autorDao.listar();
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public List<Integer> getAutoresId() {
		return autoresId;
	}

	public void setAutoresId(List<Integer> autoresId) {
		this.autoresId = autoresId;
	}

}
