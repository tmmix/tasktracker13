package com.ert.java.training.tasktracker.repositories;

import com.ert.java.training.tasktracker.entities.Task;

import java.util.List;

public interface TaskRepository {
    boolean createTask(Task newTask);
    Task getTaskById(Long id);
    Task updateTask(Task task);
    boolean deleteTaskById(Long id);
    boolean taskIdExists(Long id);
    void setTaskStatus(Long id, Task.Status status);
    List<Task> getAll();
    List<Task> getTasksByStatus(Task.Status status);
    List<Task> getTasksSortedByStatus();
    long countTasksByStatus(Task.Status status);
}
