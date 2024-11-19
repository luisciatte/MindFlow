package modelo.dao.tipotarefa;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import modelo.entidade.factory.ConexaoFactory;
import modelo.entidade.tipotarefa.TipoTarefa;

public class TipoTarefaDAOImpl implements TipoTarefaDAO {

    private final ConexaoFactory fabrica;

    public TipoTarefaDAOImpl() {
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

    // Inserir um novo tipo de tarefa no banco de dados
    public void inserirTipoTarefa(TipoTarefa tipoTarefa) {
        Session sessao = null;
        try {
            sessao = abrirSessao(sessao);
            sessao.save(tipoTarefa);
            sessao.getTransaction().commit();
        } catch (Exception exception) {
            erroSessao(sessao, exception);
        } finally {
            fecharSessao(sessao);
        }
    }

    // Deletar um tipo de tarefa do banco de dados
    public void deletarTipoTarefa(TipoTarefa tipoTarefa) {
        Session sessao = null;
        try {
            sessao = abrirSessao(sessao);
            sessao.delete(tipoTarefa);
            sessao.getTransaction().commit();
        } catch (Exception exception) {
            erroSessao(sessao, exception);
        } finally {
            fecharSessao(sessao);
        }
    }

    // Atualizar os dados de um tipo de tarefa no banco de dados
    public void atualizarTipoTarefa(TipoTarefa tipoTarefa) {
        Session sessao = null;
        try {
            sessao = abrirSessao(sessao);
            sessao.update(tipoTarefa);
            sessao.getTransaction().commit();
        } catch (Exception exception) {
            erroSessao(sessao, exception);
        } finally {
            fecharSessao(sessao);
        }
    }

    // Recuperar um tipo de tarefa pelo ID
    public TipoTarefa recuperarTipoTarefaPorIdUsaurio(Long id) {
        Session sessao = null;
        TipoTarefa tipoTarefa = null;

        try {
            sessao = abrirSessao(sessao);
            CriteriaBuilder contrutor = sessao.getCriteriaBuilder();
            CriteriaQuery<TipoTarefa> criteria = contrutor.createQuery(TipoTarefa.class);
            Root<TipoTarefa> raizDesenvolvedor = criteria.from(TipoTarefa.class);
            criteria.select(raizDesenvolvedor).where(contrutor.equal(raizDesenvolvedor.get("idTipoTarefa"), id));
            tipoTarefa = sessao.createQuery(criteria).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fecharSessao(sessao);
        }

        return tipoTarefa;
    }

    // Recuperar todos os tipos de tarefa
    public List<TipoTarefa> recuperarTipoTarefas() {
        Session sessao = null;
        List<TipoTarefa> tipoTarefas = null;
        try {
            sessao = abrirSessao(sessao);
            CriteriaBuilder construtor = sessao.getCriteriaBuilder();
            CriteriaQuery<TipoTarefa> criteria = construtor.createQuery(TipoTarefa.class);
            Root<TipoTarefa> raizCliente = criteria.from(TipoTarefa.class);
            criteria.select(raizCliente);
            tipoTarefas = sessao.createQuery(criteria).getResultList();
            sessao.getTransaction().commit();
        } catch (Exception exception) {
            erroSessao(sessao, exception);
        } finally {
            fecharSessao(sessao);
        }
        return tipoTarefas;
    }
}