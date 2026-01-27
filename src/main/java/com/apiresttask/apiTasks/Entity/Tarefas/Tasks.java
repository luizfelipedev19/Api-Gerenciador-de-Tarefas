package com.apiresttask.apiTasks.Entity.Tarefas;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "tarefas")
public class Tasks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String description;
    private Boolean status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_criacao", nullable = false)
    private Date data_criacao;

    public Tasks(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getDataDeCriacao() {
        return data_criacao;
    }

    public void setDataDeCriacao(Date dataDeCriacao) {
        this.data_criacao = dataDeCriacao;
    }

}
