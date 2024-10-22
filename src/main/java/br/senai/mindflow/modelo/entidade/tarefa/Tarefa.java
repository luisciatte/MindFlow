package br.senai.mindflow.modelo.entidade.tarefa;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.senai.mindflow.modelo.entidade.desenvolvedor.Desenvolvedor;
import br.senai.mindflow.modelo.entidade.usuario.Usuario;
import br.senai.mindflow.modelo.enumeracao.statusTarefa.StatusTarefa;

@Entity
@Table(name = "tarefa")
public class Tarefa {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Enumerated(EnumType.STRING)
	private StatusTarefa status;

	@Column(name = "titulo", nullable = false)
	private String titulo;

	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "desenvolvedor_id", nullable = false)
	private Desenvolvedor desenvolvedor;

	@Column(name = "descricao", length = 500)
	private String descricao;

	@Column(name = "data_criacao")
	private Date dataCriacao;

	@Column(name = "data_conclusao")
	private Date dataConcluddddsao;

	@Column(name = "prazo", nullable = false)
	private LocalDate prazo;

	public Tarefa() {
	}

	public Tarefa(Long id, StatusTarefa status, String titulo, Usuario usuario, Desenvolvedor desenvolvedor,
			String descricao, Date dataCriacao, Date dataConcluddddsao, LocalDate prazo) {
		super();
		this.id = id;
		this.status = status;
		this.titulo = titulo;
		this.usuario = usuario;
		this.desenvolvedor = desenvolvedor;
		this.descricao = descricao;
		this.dataCriacao = dataCriacao;
		this.dataConcluddddsao = dataConcluddddsao;
		this.prazo = prazo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public StatusTarefa getStatus() {
		return status;
	}

	public void setStatus(StatusTarefa status) {
		this.status = status;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Desenvolvedor getDesenvolvedor() {
		return desenvolvedor;
	}

	public void setDesenvolvedor(Desenvolvedor desenvolvedor) {
		this.desenvolvedor = desenvolvedor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataConcluddddsao() {
		return dataConcluddddsao;
	}

	public void setDataConcluddddsao(Date dataConcluddddsao) {
		this.dataConcluddddsao = dataConcluddddsao;
	}

	public LocalDate getPrazo() {
		return prazo;
	}

	public void setPrazo(LocalDate prazo) {
		this.prazo = prazo;
	}

}
