package modelo.principal;

import modelo.dao.tarefa.TarefaDAOImpl;
import modelo.entidade.tarefa.Tarefa;

public class Main {

	public static void main(String[] args) {
		
		TarefaDAOImpl tarefaDAO = new TarefaDAOImpl();
		
		Tarefa tarefa = tarefaDAO.recuperarTarefaPorIdUsaurio(1L);
		
	}
}
