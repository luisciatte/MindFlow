package modelo.controle.servlet.tarefa;

import java.io.FileOutputStream;
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

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import modelo.dao.tarefa.TarefaDAO;
import modelo.dao.tarefa.TarefaDAOImpl;
import modelo.dao.tipotarefa.TipoTarefaDAO;
import modelo.dao.tipotarefa.TipoTarefaDAOImpl;
import modelo.entidade.desenvolvedor.Desenvolvedor;
import modelo.entidade.tarefa.Tarefa;
import modelo.entidade.tipotarefa.TipoTarefa;
import modelo.enumeracao.categoria.Categoria;
import modelo.enumeracao.status.Status;


@WebServlet(urlPatterns = { "/inserir-tarefa", "/deletar-tarefa", "/alterar-tarefa", "/gerar-relatorio" })
public class TarefaServlet extends HttpServlet implements Serializable {

	private static final long serialVersionUID = 1L;

	// Instâncias dos DAOs para manipulação de tarefas e tipos de tarefa
	private TarefaDAO daoTarefa;
	private TipoTarefaDAO daoTipoTarefa;

	/**
	 * Método de inicialização do servlet, instanciando os DAOs.
	 */
	public void init() {
		daoTarefa = new TarefaDAOImpl();
		daoTipoTarefa = new TipoTarefaDAOImpl();
	}

	/**
	 * Método para processar requisições POST e delegá-las ao método doGet.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * Método principal para processar requisições GET com base no caminho da URL.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getServletPath(); // Obtém o caminho da requisição

		try {
			// Verifica qual ação deve ser executada
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
				case "/gerar-relatorio":
					gerarRelatorioPDF(request, response);
					break;
				default:
					retornarMenu(request, response);
					break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	/**
	 * Retorna para a página inicial.
	 */
	private void retornarMenu(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * Insere uma nova tarefa no sistema.
	 */
	private void inserirTarefa(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		HttpSession session = request.getSession();

		// Obtém o desenvolvedor da sessão
		Desenvolvedor desenvolvedor = (Desenvolvedor) session.getAttribute("desenvolvedor");

		// Cria uma nova tarefa e preenche os dados
		Tarefa tarefa = new Tarefa();
		tarefa.setDataCriacao(LocalDate.now());
		tarefa.setDataPrazo(LocalDate.parse(request.getParameter("dataPrazo")));
		tarefa.setDescricao(request.getParameter("descricao"));
		tarefa.setNomeTarefa(request.getParameter("nomeTarefa"));
		tarefa.setStatus(Status.valueOf(request.getParameter("status")));
		tarefa.getDesenvolvedores().add(desenvolvedor);

		// Define a data de conclusão se o status for "CONCLUIDO"
		if (tarefa.getStatus() == Status.CONCLUIDO) {
			tarefa.setDataConclusao(LocalDate.now());
		}

		// Cria e associa o tipo de tarefa
		TipoTarefa tipoTarefa = new TipoTarefa();
		tipoTarefa.setCategoria(Categoria.valueOf(request.getParameter("categoria")));
		tipoTarefa.setPrioridade(Integer.parseInt(request.getParameter("prioridade")));
		tipoTarefa.setTarefa(tarefa);

		// Insere a tarefa e o tipo no banco de dados
		daoTarefa.inserirTarefa(tarefa);
		daoTipoTarefa.inserirTipoTarefa(tipoTarefa);

		// Redireciona para a página de login
		RequestDispatcher dispatcher = request.getRequestDispatcher("logar-usuario");
		dispatcher.forward(request, response);
	}

	/**
	 * Atualiza uma tarefa existente.
	 */
	private void alterarTarefa(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {

		// Recupera os dados da tarefa e tipo de tarefa
		TipoTarefa tipoTarefa = daoTipoTarefa
				.recuperarTipoTarefaPorIdUsaurio(Long.parseLong(request.getParameter("idTipoTarefa")));
		Tarefa tarefa = daoTarefa.recuperarTarefaPorIdUsaurio(tipoTarefa.getTarefa().getIdTarefa());

		// Atualiza os campos da tarefa
		tarefa.setDataPrazo(LocalDate.parse(request.getParameter("dataPrazo")));
		tarefa.setDescricao(request.getParameter("descricao"));
		tarefa.setNomeTarefa(request.getParameter("nomeTarefa"));
		tarefa.setStatus(Status.valueOf(request.getParameter("status")));
		if (tarefa.getStatus() == Status.CONCLUIDO) {
			tarefa.setDataConclusao(LocalDate.now());
		}

		// Atualiza os campos do tipo de tarefa
		tipoTarefa.setCategoria(Categoria.valueOf(request.getParameter("categoria")));
		tipoTarefa.setPrioridade(Integer.parseInt(request.getParameter("prioridade")));
		tipoTarefa.setTarefa(tarefa);

		// Atualiza as informações no banco de dados
		daoTarefa.atualizarTarefa(tarefa);
		daoTipoTarefa.atualizarTipoTarefa(tipoTarefa);

		// Redireciona para a página de login
		RequestDispatcher dispatcher = request.getRequestDispatcher("logar-usuario");
		dispatcher.forward(request, response);
	}

	/**
	 * Deleta uma tarefa existente.
	 */
	private void deletarTarefa(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {

		// Recupera a tarefa e tipo de tarefa pelo ID
		Tarefa tarefa = daoTarefa.recuperarTarefaPorIdUsaurio(Long.parseLong(request.getParameter("idTarefa")));
		TipoTarefa tipoTarefa = daoTipoTarefa
				.recuperarTipoTarefaPorIdUsaurio(Long.parseLong(request.getParameter("idTipoTarefa")));

		// Remove os registros do banco de dados
		daoTarefa.deletarTarefa(tarefa);
		daoTipoTarefa.deletarTipoTarefa(tipoTarefa);

		// Redireciona para a página inicial do desenvolvedor
		RequestDispatcher dispatcher = request.getRequestDispatcher("paginas/desenvolvedor/home-dev.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * Gera um relatório em PDF com os detalhes de uma tarefa.
	 */
	public void gerarRelatorioPDF(HttpServletRequest request, HttpServletResponse response)
	        throws SQLException, ServletException, IOException {

	    System.out.println("Iniciando geração de relatório");

	    // Recupera a tarefa e tipo de tarefa pelo ID
	    TipoTarefa tipoTarefa = daoTipoTarefa
	            .recuperarTipoTarefaPorIdUsaurio(Long.parseLong(request.getParameter("idTipoTarefa")));
	    Tarefa tarefa = daoTarefa.recuperarTarefaPorIdUsaurio(tipoTarefa.getTarefa().getIdTarefa());

	    response.setContentType("application/pdf");
	    response.setHeader("Content-Disposition", "attachment; filename=Relatorio.pdf");

	    try {
	        // Criação do documento PDF
	        Document document = new Document();
	        PdfWriter.getInstance(document, response.getOutputStream());
	        document.open();

	        // Adiciona informações ao PDF
	        document.add(new Paragraph("Relatório da Tarefa"));
	        document.add(new Paragraph("ID: " + tarefa.getIdTarefa()));
	        document.add(new Paragraph("Título: " + tarefa.getNomeTarefa()));
	        document.add(new Paragraph("Descrição: " + tarefa.getDescricao()));
	        document.add(new Paragraph("Status: " + tarefa.getStatus()));
	        document.add(new Paragraph("Data Criação: " + tarefa.getDataCriacao()));
	        document.add(new Paragraph("Data Limite: " + tarefa.getDataPrazo()));
	        document.add(new Paragraph("Data Conclusão: " + tarefa.getDataConclusao()));
	        document.add(new Paragraph("ID Tipo Tarefa: " + tipoTarefa.getIdTipoTarefa()));
	        document.add(new Paragraph("Prioridade: " + tipoTarefa.getPrioridade()));
	        document.add(new Paragraph("Categoria: " + tipoTarefa.getCategoria()));
	        document.close();

	        System.out.println("Relatório gerado com sucesso");
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
