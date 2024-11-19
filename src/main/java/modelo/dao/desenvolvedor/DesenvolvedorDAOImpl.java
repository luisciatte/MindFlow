package modelo.dao.desenvolvedor;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import modelo.entidade.desenvolvedor.Desenvolvedor;
import modelo.entidade.factory.ConexaoFactory;
import modelo.entidade.usuario.Usuario;

public class DesenvolvedorDAOImpl implements DesenvolvedorDAO {

    private final ConexaoFactory fabrica;

    public DesenvolvedorDAOImpl() {
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

    // Inserir um novo desenvolvedor no banco de dados
    public boolean inserirDesenvolvedor(Desenvolvedor desenvolvedor) {
        Session sessao = null;
        try {
            sessao = abrirSessao(sessao);
            sessao.save(desenvolvedor);
            sessao.getTransaction().commit();
        } catch (Exception exception) {
            erroSessao(sessao, exception);
            return false;
        } finally {
            fecharSessao(sessao);
        }
        return true;
    }

    // Deletar um desenvolvedor do banco de dados
    public boolean deletarDesenvolvedor(Desenvolvedor desenvolvedor) {
        Session sessao = null;
        try {
            sessao = abrirSessao(sessao);
            sessao.delete(desenvolvedor);
            sessao.getTransaction().commit();
        } catch (Exception exception) {
            erroSessao(sessao, exception);
            return false;
        } finally {
            fecharSessao(sessao);
        }
        return true;
    }

    // Atualizar os dados de um desenvolvedor no banco de dados
    public boolean atualizarDesenvolvedor(Desenvolvedor desenvolvedor) {
        Session sessao = null;
        try {
            sessao = abrirSessao(sessao);
            sessao.update(desenvolvedor);
            sessao.getTransaction().commit();
        } catch (Exception exception) {
            erroSessao(sessao, exception);
            return false;
        } finally {
            fecharSessao(sessao);
        }
        return true;
    }

    // Recuperar um desenvolvedor pelo ID do usuário
    public Desenvolvedor recuperarDesenvolvedorPorIdUsaurio(Long id) {
        Session sessao = null;
        Desenvolvedor desevolvedor = null;

        try {
            sessao = abrirSessao(sessao);
            CriteriaBuilder contrutor = sessao.getCriteriaBuilder();
            CriteriaQuery<Desenvolvedor> criteria = contrutor.createQuery(Desenvolvedor.class);
            Root<Desenvolvedor> raizDesenvolvedor = criteria.from(Desenvolvedor.class);
            Join<Desenvolvedor, Usuario> join = raizDesenvolvedor.join("usuario");
            criteria.select(raizDesenvolvedor).where(contrutor.equal(raizDesenvolvedor.get("usuario"), id));
            desevolvedor = sessao.createQuery(criteria).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fecharSessao(sessao);
        }

        return desevolvedor;
    }

    // Recuperar todos os desenvolvedores
    public List<Desenvolvedor> recuperarDesenvolvedores() {
        Session sessao = null;
        List<Desenvolvedor> desenvolvedores = null;
        try {
            sessao = abrirSessao(sessao);
            CriteriaBuilder construtor = sessao.getCriteriaBuilder();
            CriteriaQuery<Desenvolvedor> criteria = construtor.createQuery(Desenvolvedor.class);
            Root<Desenvolvedor> raizCliente = criteria.from(Desenvolvedor.class);
            criteria.select(raizCliente);
            desenvolvedores = sessao.createQuery(criteria).getResultList();
            sessao.getTransaction().commit();
        } catch (Exception exception) {
            erroSessao(sessao, exception);
        } finally {
            fecharSessao(sessao);
        }
        return desenvolvedores;
    }
}
