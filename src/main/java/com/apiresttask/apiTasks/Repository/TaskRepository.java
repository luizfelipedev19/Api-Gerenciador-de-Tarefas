package com.apiresttask.apiTasks.Repository;

import com.apiresttask.apiTasks.Entity.Tarefas.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Tasks, Long> {

}
