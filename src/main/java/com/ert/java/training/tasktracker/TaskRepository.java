package com.ert.java.training.tasktracker;

import java.util.ArrayList;

public interface TaskRepository {
    Task findById(Long id);
    boolean createTask(Task newTask);
    Task updateTask(Task task);
    boolean deleteTaskById(Long id);
    ArrayList<Task> getAll();
    ArrayList<Task> getTasksByStatus(Task.Status status);
    void setTaskStatus(Long id, Task.Status status);
    boolean taskIdExists(Long id);
    ArrayList<Task> getTasksSortedByStatus();
    long countTasksByStatus(Task.Status status);
}
