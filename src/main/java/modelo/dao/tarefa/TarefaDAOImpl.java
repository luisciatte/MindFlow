package modelo.dao.tarefa;

import java.io.FileOutputStream;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.pdfbox.pdmodel.PDPage;
import org.hibernate.Session;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import modelo.entidade.factory.ConexaoFactory;
import modelo.entidade.tarefa.Tarefa;
import modelo.entidade.tipotarefa.TipoTarefa;

public class TarefaDAOImpl implements TarefaDAO {

    private final ConexaoFactory fabrica;

    public TarefaDAOImpl() {
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

    // Inserir uma nova tarefa no banco de dados
    public void inserirTarefa(Tarefa tarefa) {
        Session sessao = null;
        try {
            sessao = abrirSessao(sessao);
            sessao.save(tarefa);
            sessao.getTransaction().commit();
        } catch (Exception exception) {
            erroSessao(sessao, exception);
        } finally {
            fecharSessao(sessao);
        }
    }

    // Deletar uma tarefa do banco de dados
    public void deletarTarefa(Tarefa tarefa) {
        Session sessao = null;
        try {
            sessao = abrirSessao(sessao);
            sessao.delete(tarefa);
            sessao.getTransaction().commit();
        } catch (Exception exception) {
            erroSessao(sessao, exception);
        } finally {
            fecharSessao(sessao);
        }
    }

    // Atualizar os dados de uma tarefa no banco de dados
    public void atualizarTarefa(Tarefa tarefa) {
        Session sessao = null;
        try {
            sessao = abrirSessao(sessao);
            sessao.update(tarefa);
            sessao.getTransaction().commit();
        } catch (Exception exception) {
            erroSessao(sessao, exception);
        } finally {
            fecharSessao(sessao);
        }
    }

    // Recuperar uma tarefa pelo ID
    public Tarefa recuperarTarefaPorIdUsaurio(Long id) {
        Session sessao = null;
        Tarefa tarefa = null;

        try {
            sessao = abrirSessao(sessao);
            CriteriaBuilder contrutor = sessao.getCriteriaBuilder();
            CriteriaQuery<Tarefa> criteria = contrutor.createQuery(Tarefa.class);
            Root<Tarefa> raizDesenvolvedor = criteria.from(Tarefa.class);
            criteria.select(raizDesenvolvedor).where(contrutor.equal(raizDesenvolvedor.get("idTarefa"), id));
            tarefa = sessao.createQuery(criteria).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fecharSessao(sessao);
        }

        return tarefa;
    }

    // Recuperar todas as tarefas
    public List<Tarefa> recuperarTarefas() {
        Session sessao = null;
        List<Tarefa> tarefas = null;
        try {
            sessao = abrirSessao(sessao);
            CriteriaBuilder construtor = sessao.getCriteriaBuilder();
            CriteriaQuery<Tarefa> criteria = construtor.createQuery(Tarefa.class);
            Root<Tarefa> raizCliente = criteria.from(Tarefa.class);
            criteria.select(raizCliente);
            tarefas = sessao.createQuery(criteria).getResultList();
            sessao.getTransaction().commit();
        } catch (Exception exception) {
            erroSessao(sessao, exception);
        } finally {
            fecharSessao(sessao);
        }
        return tarefas;
    }
}
