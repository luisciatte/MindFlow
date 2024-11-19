package modelo.dao.usuario;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import modelo.entidade.factory.ConexaoFactory;
import modelo.entidade.usuario.Usuario;

public class UsuarioDAOImpl implements UsuarioDAO {
	
	private final ConexaoFactory fabrica;

	public UsuarioDAOImpl() {
		fabrica = new ConexaoFactory();
	}

    // Método para tratar erros durante a sessão
	private void erroSessao(Session sessao, Exception exception) {
		exception.printStackTrace();
		if (sessao.getTransaction() != null) {
			sessao.getTransaction().rollback();
		}
	}

    // Método para fechar a sessão
	private void fecharSessao(Session sessao) {
		if (sessao != null) {
			sessao.close();
		}
	}

    // Método para abrir uma nova sessão
	private Session abrirSessao(Session sessao) {
		sessao = fabrica.getConexao().openSession();
		sessao.beginTransaction();
		return sessao;
	}

    // Inserir um novo usuário no banco de dados
	public void inserirUsuario(Usuario usuario) {
		Session sessao = null;
		try {
			sessao = abrirSessao(sessao);
			sessao.save(usuario);
			sessao.getTransaction().commit();
		} catch (Exception exception) {
			erroSessao(sessao, exception);
		} finally {
			fecharSessao(sessao);
		}
	}

    // Deletar um usuário do banco de dados
	public void deletarUsuario(Usuario usuario) {
		Session sessao = null;
		try {
			sessao = abrirSessao(sessao);
			sessao.delete(usuario);
			sessao.getTransaction().commit();
		} catch (Exception exception) {
			erroSessao(sessao, exception);
		} finally {
			fecharSessao(sessao);
		}
	}

    // Atualizar os dados de um usuário no banco de dados
	public void atualizarUsuario(Usuario usuario) {
		Session sessao = null;
		try {
			sessao = abrirSessao(sessao);
			sessao.update(usuario);
			sessao.getTransaction().commit();
		} catch (Exception exception) {
			erroSessao(sessao, exception);
		} finally {
			fecharSessao(sessao);
		}
	}

    // Recuperar todos os usuários do banco de dados
	public List<Usuario> recuperarUsuarios() {
		Session sessao = null;
		List<Usuario> usuarios = null;
		try {
			sessao = abrirSessao(sessao);
			CriteriaBuilder construtor = sessao.getCriteriaBuilder();
			CriteriaQuery<Usuario> criteria = construtor.createQuery(Usuario.class);
			Root<Usuario> raizCliente = criteria.from(Usuario.class);
			criteria.select(raizCliente);
			usuarios = sessao.createQuery(criteria).getResultList();
			sessao.getTransaction().commit();
		} catch (Exception exception) {
			erroSessao(sessao, exception);
		} finally {
			fecharSessao(sessao);
		}
		return usuarios;
	}
}
