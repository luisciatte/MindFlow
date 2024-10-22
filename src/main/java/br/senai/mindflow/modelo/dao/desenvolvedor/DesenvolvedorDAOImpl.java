package br.senai.mindflow.modelo.dao.desenvolvedor;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import br.senai.mindflow.modelo.entidade.desenvolvedor.Desenvolvedor;
import br.senai.mindflow.modelo.factory.ConexaoFactory;

public class DesenvolvedorDAOImpl implements DesenvolvedorDAO {

	private ConexaoFactory fabrica;

	public DesenvolvedorDAOImpl() {
		fabrica = new ConexaoFactory();
	}

	public void inserirDesenvolvedor(Desenvolvedor desenvolvedorsangue) {

		Session sessao = null;

		try {

			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();

			sessao.save(desenvolvedorsangue);

			sessao.getTransaction().commit();

		} catch (Exception sqlException) {

			sqlException.printStackTrace();

			if (sessao.getTransaction() != null) {
				sessao.getTransaction().rollback();
			}

		} finally {

			if (sessao != null) {
				sessao.close();
			}
		}
	}

	public void deletarDesenvolvedor(Desenvolvedor desenvolvedorsangue) {

		Session sessao = null;

		try {

			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();

			sessao.delete(desenvolvedorsangue);

			sessao.getTransaction().commit();

		} catch (Exception sqlException) {

			sqlException.printStackTrace();

			if (sessao.getTransaction() != null) {
				sessao.getTransaction().rollback();
			}

		} finally {

			if (sessao != null) {
				sessao.close();
			}
		}
	}

	public void atualizarDesenvolvedor(Desenvolvedor desenvolvedorsangue) {

		Session sessao = null;

		try {

			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();

			sessao.update(desenvolvedorsangue);

			sessao.getTransaction().commit();

		} catch (Exception sqlException) {

			sqlException.printStackTrace();

			if (sessao.getTransaction() != null) {
				sessao.getTransaction().rollback();
			}

		} finally {

			if (sessao != null) {
				sessao.close();
			}
		}
	}

	public List<Desenvolvedor> recuperarDesenvolvedores() {

		Session sessao = null;
		List<Desenvolvedor> desenvolvedores = null;

		try {

			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();

			CriteriaBuilder construtor = sessao.getCriteriaBuilder();

			CriteriaQuery<Desenvolvedor> criteria = construtor.createQuery(Desenvolvedor.class);
			Root<Desenvolvedor> raizDesenvolvedor = criteria.from(Desenvolvedor.class);

			criteria.select(raizDesenvolvedor);

			desenvolvedores = sessao.createQuery(criteria).getResultList();

			sessao.getTransaction().commit();

		} catch (Exception sqlException) {

			sqlException.printStackTrace();

			if (sessao.getTransaction() != null) {
				sessao.getTransaction().rollback();
			}

		} finally {

			if (sessao != null) {
				sessao.close();
			}
		}

		return desenvolvedores;
	}
}
