package modelo.entidade.tarefa;

import java.time.LocalDate;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.entidade.desenvolvedor.Desenvolvedor;
import modelo.enumeracao.status.Status;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Tarefa.class)
public abstract class Tarefa_ {

	public static volatile SingularAttribute<Tarefa, String> nomeTarefa;
	public static volatile SingularAttribute<Tarefa, LocalDate> dataPrazo;
	public static volatile SingularAttribute<Tarefa, LocalDate> dataConclusao;
	public static volatile SingularAttribute<Tarefa, Long> idTarefa;
	public static volatile SingularAttribute<Tarefa, LocalDate> dataCriacao;
	public static volatile SetAttribute<Tarefa, Desenvolvedor> desenvolvedores;
	public static volatile SingularAttribute<Tarefa, String> descricao;
	public static volatile SingularAttribute<Tarefa, Status> status;

	public static final String NOME_TAREFA = "nomeTarefa";
	public static final String DATA_PRAZO = "dataPrazo";
	public static final String DATA_CONCLUSAO = "dataConclusao";
	public static final String ID_TAREFA = "idTarefa";
	public static final String DATA_CRIACAO = "dataCriacao";
	public static final String DESENVOLVEDORES = "desenvolvedores";
	public static final String DESCRICAO = "descricao";
	public static final String STATUS = "status";

}

