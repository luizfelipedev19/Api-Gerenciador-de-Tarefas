package com.apiresttask.apiTasks.Service;

import com.apiresttask.apiTasks.DTO.TaskRequest;
import com.apiresttask.apiTasks.DTO.TaskResponse;
import com.apiresttask.apiTasks.Entity.Tarefas.Tasks;
import com.apiresttask.apiTasks.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TaskService {
    /**
     * No service, fica toda regra de negócio, ou seja,
     * tudo que precisa verificar, criação de métodos, etc,
     * fica no service
     */
    @Autowired
    TaskRepository taskRepository;

    /**
     * Aqui eu converto o TaskRequest em uma entidade Task, para que tenha acesso aos atributos.
     */
    public Tasks toEntity(TaskRequest request){
        Tasks task = new Tasks();

        task.setTitulo(request.getTitulo());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        task.setDataDeCriacao(new Date());
        return task;
    }

    public TaskResponse toResponse(Tasks task){
        TaskResponse response = new TaskResponse();

        response.setId(task.getId());
        response.setTitulo(task.getTitulo());
        response.setDescription(task.getDescription());
        response.setStatus(task.getStatus());


        return response;
    }

    public TaskResponse adicionarTask(TaskRequest request){
        Tasks task = toEntity(request);
        Tasks salva = taskRepository.save(task);
        return toResponse(salva);
    }

    public List<TaskResponse> getTasks(){
        return taskRepository.findAll().stream().map(this::toResponse).toList();
    }

    /**
     * para atualizar uma tarefa ja cadastrada, precisa passar o id, onde vai
     * pegar a tarefa corretamente e a variavel booleana do status
     * vai criar uma nova variável, que recebe a variavel do repository,
     * com o metodo findById(id), que passando o id, busca o item pelo seu id.
     * Em seguida, você coloca uma excessao de execução
     */
    public TaskResponse atualizaStatus (Long id, Boolean status){
        Tasks task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task não encontrada"));

        task.setStatus(status);
        Tasks update = taskRepository.save(task);
        return toResponse(update);
    }

    public void deletarTarefa(Long id){
        Tasks task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task não encontrada"));

       taskRepository.delete(task);
    }

}
