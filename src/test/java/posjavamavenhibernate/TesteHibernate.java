package posjavamavenhibernate;

import java.util.List;

import org.hibernate.metamodel.model.domain.spi.SetPersistentAttribute;
import org.junit.Test;

import dao.DaoGeneric;
import model.TelefoneUser;
import model.UsuarioPessoa;

public class TesteHibernate {

	@Test
	public void testeHibernateUtil() { 
		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		UsuarioPessoa pessoa = new UsuarioPessoa();
		
		pessoa.setIdade(34);
		pessoa.setLogin("teste");
		pessoa.setNome("marcelo pereira");
		pessoa.setSenha("3827");
		pessoa.setSobrenome("pereira");
		pessoa.setEmail("wsnino.contato@gmail.com");
		
		daoGeneric.salvar(pessoa);
				
	}
	@Test
	public void testeBuscar() { 
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		UsuarioPessoa pessoa = new UsuarioPessoa();
		pessoa.setId(2L);;
		
		pessoa = daoGeneric.pesquisar(pessoa);
		
		System.out.println(pessoa);
	}
	
	@Test
	public void testeBuscar2() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		UsuarioPessoa pessoa = daoGeneric.pesquisar(3L, UsuarioPessoa.class);
		
		System.out.println(pessoa);
	}
	
	@Test
	public void testeUpdate() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		UsuarioPessoa pessoa = daoGeneric.pesquisar(13L, UsuarioPessoa.class);
		
		pessoa.setIdade(46);
		pessoa.setNome("Marcelo Developer java BIONEXO  ");
		pessoa.setSobrenome("pereira");
		
		pessoa = daoGeneric.updateMerge(pessoa);
		
		System.out.println(pessoa);
	}
	
	@Test
	public void testeDelete() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		UsuarioPessoa pessoa = daoGeneric.pesquisar(7L, UsuarioPessoa.class);
		
		daoGeneric.deletePoId(pessoa);		
		
	}
	
	@Test
	public void testeConsultar() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		List<UsuarioPessoa> list = daoGeneric.listar(UsuarioPessoa.class);
		
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
			System.out.println("================================================");
		}		
	}
	
	@Test
	public void testeQuerList() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		List<UsuarioPessoa> list = daoGeneric.getEntityManager()
				.createQuery("from UsuarioPessoa where idade = 46").getResultList();
		
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
			System.out.println("================================================");
		}
	}
	
	@Test
	public void testeQuerListMaxResult() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		List<UsuarioPessoa> list = daoGeneric.getEntityManager()
				.createQuery("from UsuarioPessoa order by id")
				.setMaxResults(3)
				.getResultList();
		
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
			System.out.println("================================================");
		}
	}
	
	@Test
	public void testeQuerListParameter() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createQuery("from UsuarioPessoa where nome  = :nome or sobrenome = :sobrenome")
				.setParameter("nome", "yasmin Souza")
				.setParameter("sobrenome", "Nascimento")
				.getResultList();
		
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
		}
	}
	@Test
	public void testeQuerSomaIdade() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		
		Double somaIdade = (Double) daoGeneric.getEntityManager().
			createQuery(" select avg(u.idade) from UsuarioPessoa u ").getSingleResult();
		
		System.out.println("Soma de todas as Idades é --> " + somaIdade);

	}
	
	@Test
	public void testeNameQuery() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().
				createNamedQuery("UsuarioPessoa.todos").getResultList();
		
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
			System.out.println("==================================================================================");
		}
	}
	
	@Test
	public void testeNameQuery2() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().
				createNamedQuery("UsuarioPessoa.buscarPorNome")
				.setParameter("nome", "gege")
				.getResultList();
		
		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
			System.out.println("==================================================================================");
		}
	}
	
	@Test
	public void testeGravarTelefone() {
		DaoGeneric daoGeneric = new DaoGeneric();
		
		UsuarioPessoa pessoa = (UsuarioPessoa) daoGeneric.pesquisar(9L, UsuarioPessoa.class);
		
		TelefoneUser telefoneUser = new TelefoneUser();
		telefoneUser.setTipo("casa");
		telefoneUser.setNumero("21931490");
		telefoneUser.setUsuarioPessoa(pessoa);
		
		daoGeneric.salvar(telefoneUser);
	}
	
	@Test
	public void testeConsultaTelefone() {
		DaoGeneric daoGeneric = new DaoGeneric();
		
		UsuarioPessoa pessoa = (UsuarioPessoa) daoGeneric.pesquisar(9L, UsuarioPessoa.class);
		
		for (TelefoneUser fone : pessoa.getTelefoneUsers()) {
			System.out.println(fone.getNumero());
			System.out.println(fone.getTipo());
			System.out.println(fone.getUsuarioPessoa().getNome());
			System.out.println(fone.getUsuarioPessoa().getEmail());
			System.out.println("==================================================================================");
		}
		
	}
}
