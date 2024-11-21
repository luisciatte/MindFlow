package modelo.entidade.desenvolvedor;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.entidade.tarefa.Tarefa;
import modelo.entidade.usuario.Usuario;
import modelo.enumeracao.funcaodesenvolvedor.FuncaoDesenvolvedor;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Desenvolvedor.class)
public abstract class Desenvolvedor_ {

	public static volatile SingularAttribute<Desenvolvedor, FuncaoDesenvolvedor> funcao;
	public static volatile SingularAttribute<Desenvolvedor, String> nomeDesenvolvedor;
	public static volatile SetAttribute<Desenvolvedor, Tarefa> tarefas;
	public static volatile SingularAttribute<Desenvolvedor, String> cpf;
	public static volatile SingularAttribute<Desenvolvedor, Usuario> usuario;

	public static final String FUNCAO = "funcao";
	public static final String NOME_DESENVOLVEDOR = "nomeDesenvolvedor";
	public static final String TAREFAS = "tarefas";
	public static final String CPF = "cpf";
	public static final String USUARIO = "usuario";

}

