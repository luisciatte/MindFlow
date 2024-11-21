package modelo.controle.servlet.tarefa;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.dao.tarefa.TarefaDAO;
import modelo.dao.tarefa.TarefaDAOImpl;
import modelo.dao.tipotarefa.TipoTarefaDAO;
import modelo.dao.tipotarefa.TipoTarefaDAOImpl;
import modelo.dao.usuario.UsuarioDAO;
import modelo.dao.usuario.UsuarioDAOImpl;
import modelo.entidade.desenvolvedor.Desenvolvedor;
import modelo.entidade.tarefa.Tarefa;
import modelo.entidade.tipotarefa.TipoTarefa;
import modelo.enumeracao.categoria.Categoria;
import modelo.enumeracao.status.Status;

@WebServlet(urlPatterns = {"/inserir-tarefa", "/deletar-tarefa", "/alterar-tarefa"})
public class TarefaServlet extends HttpServlet implements Serializable{

	private static final long serialVersionUID = 1L;
    private TarefaDAO daoTarefa;
    private TipoTarefaDAO daoTipoTarefa;
	
    public void init() {
		daoTarefa = new TarefaDAOImpl();
		daoTipoTarefa = new TipoTarefaDAOImpl();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getServletPath();
		
		try {
			
			switch (action) {
			
			case "/inserir-tarefa":
				inserirTarefa(request, response);
				break;
			
			case "/deletar-tarefa":
				deletarTarefa(request, response);
				break;
				
			case "/alterar-tarefa":
				alterarTarefa(request, response);
				break;
				
			default:
				retornarMenu(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	
	private void retornarMenu(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}
	
	private void inserirTarefa(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		HttpSession session = request.getSession();
		
		Desenvolvedor desenvolvedor = (Desenvolvedor) session.getAttribute("desenvolvedor");
		
		Tarefa tarefa = new Tarefa();
		
		tarefa.setDataCriacao(LocalDate.now());
		tarefa.setDataPrazo(LocalDate.parse(request.getParameter("dataPrazo")));
		tarefa.setDescricao(request.getParameter("descricao"));
		tarefa.setNomeTarefa(request.getParameter("nomeTarefa"));
		tarefa.setStatus(Status.valueOf(request.getParameter("status")));
		tarefa.getDesenvolvedores().add(desenvolvedor);
		tarefa.setDesenvolvedores(tarefa.getDesenvolvedores());
		
		TipoTarefa tipoTarefa = new TipoTarefa();
		
		tipoTarefa.setCategoria(Categoria.valueOf(request.getParameter("categoria")));
		tipoTarefa.setPrioridade(Integer.parseInt(request.getParameter("prioridade")));
		tipoTarefa.setTarefa(tarefa);
		
		daoTarefa.inserirTarefa(tarefa);
		daoTipoTarefa.inserirTipoTarefa(tipoTarefa);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("logar-usuario");
		dispatcher.forward(request, response);
		
	}
	
	private void alterarTarefa(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		
		TipoTarefa tipoTarefa = daoTipoTarefa.recuperarTipoTarefaPorIdUsaurio(Long.parseLong(request.getParameter("idTipoTarefa")));
		
		Tarefa tarefa = daoTarefa.recuperarTarefaPorIdUsaurio(tipoTarefa.getTarefa().getIdTarefa());
		
		tarefa.setDataPrazo(LocalDate.parse(request.getParameter("dataPrazo")));
		tarefa.setDescricao(request.getParameter("descricao"));
		tarefa.setNomeTarefa(request.getParameter("nomeTarefa"));
		tarefa.setStatus(Status.valueOf(request.getParameter("status")));
		
		tipoTarefa.setCategoria(Categoria.valueOf(request.getParameter("categoria")));
		tipoTarefa.setPrioridade(Integer.parseInt(request.getParameter("prioridade")));
		tipoTarefa.setTarefa(tarefa);
		
		daoTarefa.atualizarTarefa(tarefa);
		daoTipoTarefa.atualizarTipoTarefa(tipoTarefa);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("logar-usuario");
		dispatcher.forward(request, response);
		
	}
	
	private void deletarTarefa(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		
		Tarefa tarefa = daoTarefa.recuperarTarefaPorIdUsaurio(Long.parseLong(request.getParameter("idTarefa")));
		TipoTarefa tipoTarefa = daoTipoTarefa.recuperarTipoTarefaPorIdUsaurio(Long.parseLong(request.getParameter("idTipoTarefa")));
		
		daoTarefa.deletarTarefa(tarefa);
		daoTipoTarefa.deletarTipoTarefa(tipoTarefa);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("paginas/desenvolvedor/home-dev.jsp");
		dispatcher.forward(request, response);
	}
}
