package posjavamavenhibernate;

import org.junit.Test;

import dao.DaoGeneric;
import model.UsuarioPessoa;

public class TesteHibernate {

	@Test
	public void testeHibernateUtil() { 
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		UsuarioPessoa pessoa = new UsuarioPessoa();
		
		pessoa.setIdade(45);
		pessoa.setLogin("teste");
		pessoa.setNome("Marcelo Pereira");
		pessoa.setSenha("123");
		pessoa.setSobrenome("Nascimento");
		pessoa.setEmail("wsnino.contato@gmail.com");
		
		daoGeneric.salvar(pessoa);
	}
}
