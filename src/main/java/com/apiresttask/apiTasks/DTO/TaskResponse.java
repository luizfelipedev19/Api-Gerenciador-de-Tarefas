package com.apiresttask.apiTasks.DTO;

import java.util.Date;

public class TaskResponse {

    private Long id;
    private String titulo;
    private String description;
    private Boolean status;
    private Date data_cricao;


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

    public Date getData_cricao() {
        return data_cricao;
    }

    public void setData_cricao(Date data_cricao) {
        this.data_cricao = data_cricao;
    }
}
