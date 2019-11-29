package com.ert.java.training.tasktracker;

import java.util.List;

public interface TaskRepository {
    boolean createTask(Task newTask);
    Task getTaskById(Long id);
    Task updateTask(Task task);
    boolean deleteTaskById(Long id);
    List<Task> getAll();
    List<Task> getTasksByStatus(Task.Status status);
    void setTaskStatus(Long id, Task.Status status);
    boolean taskIdExists(Long id);
    List<Task> getTasksSortedByStatus();
    long countTasksByStatus(Task.Status status);
}
