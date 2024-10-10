package br.senai.mindflow.modelo.entidade.administrador;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.senai.mindflow.modelo.entidade.usuario.Usuario;

@Entity
@Table(name = "administrador")
public class Administrador {

	private static final long serialVersionUID = 1L;

	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "senha", nullable = false)
    private String senha;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "administrador_id")
    private List<Usuario> usuarios;

	public Administrador() {}

	public Administrador(String nome, String email, String senha, List<Usuario> usuarios) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.usuarios = usuarios;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}