package modelo.controle.servlet.desenvolvedor;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.dao.desenvolvedor.DesenvolvedorDAO;
import modelo.dao.desenvolvedor.DesenvolvedorDAOImpl;
import modelo.dao.usuario.UsuarioDAO;
import modelo.dao.usuario.UsuarioDAOImpl;
import modelo.entidade.desenvolvedor.Desenvolvedor;
import modelo.entidade.usuario.Usuario;
import modelo.enumeracao.funcaodesenvolvedor.FuncaoDesenvolvedor;


@WebServlet(urlPatterns = {"/cadastrar-desenvolvedor", "/inserir-desenvolvedor"})
public class DesenvolvedorServlet extends HttpServlet implements Serializable {

    private static final long serialVersionUID = 1L;

    // Objetos DAO para acessar o banco de dados.
    private DesenvolvedorDAO daoDesenvolvedor;
    private UsuarioDAO daoUsuario;

    /**
     * Método chamado na inicialização do servlet. 
     * Instancia os DAOs que serão usados para interagir com o banco de dados.
     */
    public void init() {
        daoDesenvolvedor = new DesenvolvedorDAOImpl();
        daoUsuario = new UsuarioDAOImpl();
    }

    /**
     * Repassa requisições POST para o método doGet.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * Trata requisições GET. Identifica qual ação deve ser realizada com base no caminho da URL.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Obtém o caminho da URL para decidir a ação.
        String action = request.getServletPath();
        
        try {
            switch (action) {
                case "/cadastrar-desenvolvedor":
                    mostrarCadastroDesenvolvedor(request, response);
                    break;
                case "/inserir-desenvolvedor":
                    inserirDesenvolvedor(request, response);
                    break;
                default:
                    retornarMenu(request, response);
                    break;
            }
        } catch (SQLException ex) {
            // Lança uma exceção se ocorrer um erro no banco de dados.
            throw new ServletException(ex);
        }
    }

    /**
     * Redireciona para a página de cadastro de desenvolvedor.
     */
    private void mostrarCadastroDesenvolvedor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher(
            "paginas/desenvolvedor/cadastrar-desenvolvedor.jsp"
        );
        dispatcher.forward(request, response);
    }

    /**
     * Redireciona para a página principal do sistema.
     */
    private void retornarMenu(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Insere um novo desenvolvedor no banco de dados.
     * - Primeiro insere o usuário correspondente.
     * - Depois insere o desenvolvedor, associando-o ao usuário criado.
     */
    private void inserirDesenvolvedor(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, ServletException, IOException {

        // Cria um novo objeto Usuario e preenche com os dados do formulário.
        Usuario usuario = new Usuario();
        usuario.setEmail(request.getParameter("email"));
        usuario.setSenha(request.getParameter("senha"));

        // Insere o usuário no banco.
        daoUsuario.inserirUsuario(usuario);

        // Cria um novo objeto Desenvolvedor e preenche com os dados do formulário.
        Desenvolvedor desenvolvedor = new Desenvolvedor();
        desenvolvedor.setCpf(request.getParameter("cpf"));
        desenvolvedor.setNomeDesenvolvedor(request.getParameter("nome"));
        desenvolvedor.setFuncao(FuncaoDesenvolvedor.valueOf(request.getParameter("funcao")));
        desenvolvedor.setUsuario(usuario);

        // Insere o desenvolvedor no banco.
        daoDesenvolvedor.inserirDesenvolvedor(desenvolvedor);

        // Redireciona para a página inicial do desenvolvedor.
        RequestDispatcher dispatcher = request.getRequestDispatcher(
            "paginas/desenvolvedor/home-dev.jsp"
        );
        dispatcher.forward(request, response);
    }
}
