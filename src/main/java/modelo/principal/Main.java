package modelo.principal;

import java.time.LocalDate;

import modelo.dao.tarefa.TarefaDAOImpl;
import modelo.dao.tipotarefa.TipoTarefaDAOImpl;
import modelo.entidade.tarefa.Tarefa;
import modelo.entidade.tipotarefa.TipoTarefa;
import modelo.enumeracao.categoria.Categoria;
import modelo.enumeracao.status.Status;

public class Main {

	public static void main(String[] args) {
		
		TarefaDAOImpl tarefaDAO = new TarefaDAOImpl();
		Tarefa tarefa = new Tarefa();
		
		tarefa.setDataCriacao(LocalDate.now());
		tarefa.setDataPrazo(LocalDate.of(2024, 11, 22));
		tarefa.setDescricao("Fazer Programa");
		tarefa.setNomeTarefa("Nome Tarefa");
		tarefa.setStatus(Status.EM_ANDAMENTO);
		
		TipoTarefaDAOImpl tipoTarefaDAO = new TipoTarefaDAOImpl();
		TipoTarefa tipoTarefa = new TipoTarefa();
		
		tipoTarefa.setTarefa(tarefa);
		tipoTarefa.setPrioridade(1);
		tipoTarefa.setCategoria(Categoria.PESSOAL);
		
		tarefaDAO.inserirTarefa(tarefa);
		tipoTarefaDAO.inserirTipoTarefa(tipoTarefa);
		

		
		for (Tarefa tarefaa : tarefaDAO.recuperarTarefas()) {
			if(tarefa.getStatus() == Status.valueOf("EM_ANDAMENTO")) {
				System.out.println("true");
			}
		}
	}

}
