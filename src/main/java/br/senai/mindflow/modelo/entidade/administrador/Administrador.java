package br.senai.mindflow.modelo.entidade.administrador;

public class Administrador {
	private String nome;
	private String email;
	private String senha;
	private List<Usuario> usuarios;

	public Administrador() {}

	public Administrador(String nome, String email, String senha, List<Usuario> usuarios) {
		super();
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