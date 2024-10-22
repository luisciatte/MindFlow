package br.senai.mindflow.modelo.factory;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class ConexaoFactory {

	public SessionFactory getConexao() {
                // Criando a configuração do Hibernate
                Configuration configuracao = new Configuration();

                // Adicionando as classes anotadas do seu sistema
                configuracao.addAnnotatedClass(br.senai.mindflow.modelo.entidade.desenvolvedor.Desenvolvedor.class);
                configuracao.addAnnotatedClass(br.senai.mindflow.modelo.entidade.tarefa.Tarefa.class);
                configuracao.addAnnotatedClass(br.senai.mindflow.modelo.entidade.usuario.Usuario.class);

                configuracao.configure("hibernate.cfg.xml");

                ServiceRegistry servico = new StandardServiceRegistryBuilder().applySettings(configuracao.getProperties()).build();
                return configuracao.buildSessionFactory(servico);
    }
}