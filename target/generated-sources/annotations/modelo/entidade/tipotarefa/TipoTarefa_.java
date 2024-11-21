package modelo.entidade.tipotarefa;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.entidade.tarefa.Tarefa;
import modelo.enumeracao.categoria.Categoria;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TipoTarefa.class)
public abstract class TipoTarefa_ {

	public static volatile SingularAttribute<TipoTarefa, Integer> prioridade;
	public static volatile SingularAttribute<TipoTarefa, Tarefa> tarefa;
	public static volatile SingularAttribute<TipoTarefa, Categoria> categoria;
	public static volatile SingularAttribute<TipoTarefa, Long> idTipoTarefa;

	public static final String PRIORIDADE = "prioridade";
	public static final String TAREFA = "tarefa";
	public static final String CATEGORIA = "categoria";
	public static final String ID_TIPO_TAREFA = "idTipoTarefa";

}

