package br.senai.mindflow.modelo.entidade.usuario;

import java.util.ArrayList;

public class Usuario {
    private String nome;
    private String email;
    private String senha;
    private List<Tarefa> tarefas;

    // Construtor
    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tarefas = new ArrayList<>();
    }

    // Métodos
    public void cadastrarTarefa(Tarefa tarefa) {
        tarefas.add(tarefa);
    }

    public void excluirTarefa(Tarefa tarefa) {
        tarefas.remove(tarefa);
    }

    public List<Tarefa> consultarTarefas() {
        return tarefas;
    }

    public void editarTarefa(Tarefa tarefa, String novoTitulo, String novaDescricao) {
        tarefa.editarTarefa(novoTitulo, novaDescricao);
    }

    public Tarefa buscarTarefaPorTitulo(String titulo) {
        for (Tarefa tarefa : tarefas) {
            if (tarefa.getTitulo().equals(titulo)) {
                return tarefa;
            }
        }
        return null;
    }

    // Getters e Setters
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getSenha() { return senha; }
    public List<Tarefa> getTarefas() { return tarefas; }
}
