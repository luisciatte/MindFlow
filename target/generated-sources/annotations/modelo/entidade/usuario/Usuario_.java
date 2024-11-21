package modelo.entidade.usuario;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Usuario.class)
public abstract class Usuario_ {

	public static volatile SingularAttribute<Usuario, String> senha;
	public static volatile SingularAttribute<Usuario, Boolean> administrador;
	public static volatile SingularAttribute<Usuario, Long> idUsuario;
	public static volatile SingularAttribute<Usuario, Boolean> bloqueado;
	public static volatile SingularAttribute<Usuario, Boolean> inativo;
	public static volatile SingularAttribute<Usuario, Integer> tentativas;
	public static volatile SingularAttribute<Usuario, String> email;

	public static final String SENHA = "senha";
	public static final String ADMINISTRADOR = "administrador";
	public static final String ID_USUARIO = "idUsuario";
	public static final String BLOQUEADO = "bloqueado";
	public static final String INATIVO = "inativo";
	public static final String TENTATIVAS = "tentativas";
	public static final String EMAIL = "email";

}

