package com.apiresttask.apiTasks.Controler;

import com.apiresttask.apiTasks.DTO.TaskRequest;
import com.apiresttask.apiTasks.DTO.TaskResponse;
import com.apiresttask.apiTasks.Service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    /**
     * O controller, nunca conversa com o banco de dados.
     * Quem é responsável por falar com o banco diretamente, é a classe
     * service. O controller, apenas fala com o service para usar o métodos http
     */
    @Autowired
    private TaskService taskService;


    //Cadastra tarefas no banco
    @PostMapping
    public TaskResponse criar(@RequestBody @Valid TaskRequest request){
    return taskService.adicionarTask(request);
    }

    //pega em formato de lista, todas as tarefas adicionadas no banco
    @GetMapping
    public List<TaskResponse> getTasks(){
        return taskService.getTasks();
    }

    @PatchMapping("/{id}/status")
    public TaskResponse atualizarStatus(@PathVariable Long id, @RequestParam Boolean status){
        return taskService.atualizaStatus(id, status);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)//isso é uma boa pratica do Rest, onde no método delete, ela retorna 204, pra sucesso.
    public void deletarTarefa(@PathVariable Long id){
        taskService.deletarTarefa(id);
    }

}
