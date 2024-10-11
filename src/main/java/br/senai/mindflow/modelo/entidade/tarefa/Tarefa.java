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
import javax.persistence.Table;

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

    @Column(name = "descricao", length = 500)
    private String descricao;

    @Column(name = "data_criacao")
    private Date dataCriacao;

    @Column(name = "data_conclusao")
    private Date dataConclusao;
    
    @Column(name = "prazo", nullable = false)
    private LocalDate prazo;


    

	public Tarefa() {}

	public Tarefa(String titulo, String descricao, Date dataCriacao, Date dataConclusao, String situacao,
			String prioridade) {
		this.titulo = titulo;
		this.descricao = descricao;
		this.dataCriacao = dataCriacao;
		this.dataConclusao = dataConclusao;
		this.situacao = situacao;
		this.prioridade = prioridade;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
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

	public Date getDataConclusao() {
		return dataConclusao;
	}

	public void setDataConclusao(Date dataConclusao) {
		this.dataConclusao = dataConclusao;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}

}
