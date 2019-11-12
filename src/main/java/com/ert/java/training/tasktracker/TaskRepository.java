package com.ert.java.training.tasktracker;

import java.util.ArrayList;

public interface TaskRepository {
    Task findById(Long id);
    boolean createTask(Task newTask);
    Task updateTask(Task task);
    boolean deleteTaskById(Long id);
    ArrayList<Task> getAll();
}
