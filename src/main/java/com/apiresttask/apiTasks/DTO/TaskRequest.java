package com.apiresttask.apiTasks.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class TaskRequest {

    /**
     * DTO, é a prática de você separar as entidades das requisições, ou seja, não expor a sua entidade
     * diretamente. Você cria classes para cada parte, request e responde.
     */

    /**
     * TaskRequest: É usado quando o cliente vai enviar dados para a API (Post ou PUT).
     * TaskResponse: É quando a API devolve dados para o cliente (GET)
     */

    //Essa anotação verifica se o texto não é null, se não está vazio ou não contenha só espaços.
    @NotBlank(message = "O título é obrigatório")
    @Size(min = 3, max = 50, message = "O titulo deve ter entre 3 e 50 caracteres!")
    private String titulo;
    @Size(max = 100, message = "A descrição pode ter no máximo 100 caracteres!")
    private String description;
    @NotNull(message = "O campo status é obrigatório")
    private Boolean status;


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
}
