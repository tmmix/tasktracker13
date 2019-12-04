package com.ert.java.training.tasktracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
@Service
public class TaskService {
    private TaskRepository taskRepository;

    public TaskService() {
    }

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Autowired
    @Qualifier(value = "hibernateTaskRepository")
    public void setTaskRepository(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void addTask(Task newTask) {
        if (taskRepository.getTaskById(newTask.getId()) != null) {
            throw new TaskException("Задача " + newTask.getId() + ": " + newTask.getTitle() + " уже существует");
        }
        if(taskRepository.createTask(newTask)) {
            System.out.println("Задача " + newTask.getId() + ": " + newTask.getTitle() + " добавлена");
        } else {
            throw new TaskException("Ошибка добавления задачи " + newTask.getId() + ": " + newTask.getTitle());
        }
    }

    public void printTaskList() {
        for (Task task: taskRepository.getAll()) {
            if (task != null) {
                System.out.println(task.taskInfo());
            }
        }
    }

    public void editTask(Task taskForUpdate) {
        Task updatedTask = taskRepository.updateTask(taskForUpdate);
        if(updatedTask != null) {
            System.out.println("Задача " + updatedTask.getId() + " обновлена");
        } else {
            throw new TaskException("Ошибка обновления задачи " + taskForUpdate.getId());
        }
    }

    public void deleteTask(Long id) {
        if(taskRepository.deleteTaskById(id)) {
            System.out.println("Задача " + id + " удалена");
        } else {
            throw new TaskException("Задача " + id + " не найдена");
        }
    }


    public void printTasksByStatus(Task.Status status) {
        taskRepository.getTasksByStatus(status).stream().forEach(task -> System.out.println(task.taskInfo()));
    }

    public void setTaskStatus(Long id, Task.Status status) {
        taskRepository.setTaskStatus(id, status);
    }

    public void checkTaskById(Long id) {
        if(taskRepository.taskIdExists(id)) {
            System.out.println("Задача с id=" + id + " существует.");
        } else {
            System.out.println("Задачи с id=" + id + " не найдено.");
        }
    }

    public void printTasksSortedByStatus(){
        taskRepository.getTasksSortedByStatus().stream().forEach(task -> System.out.println(task.taskInfo()));
    }

    public void printNumberOfTasksWithStatus(Task.Status status) {
        System.out.println("Количество задач со статусом \"" + status.getRusTitle() + "\" = " + taskRepository.countTasksByStatus(status));
    }

    public Task getTask(Long id) {
        return taskRepository.getTaskById(id);
    }

    public void saveTasks(List<Task> taskList, String filename) {
        int taskCnt = taskList.size();
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeInt(taskCnt);
            for (int i = 0; i < taskCnt; i++) {
                out.writeObject(taskList.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("В файл " + filename + " записано задач: " + taskCnt);
    }

    public List<Task> loadTasks(String filename) {
        List<Task> taskList = new ArrayList<>();
        int objCnt = 0;
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            objCnt = in.readInt();
            for (int i = 0; i < objCnt; i++) {
                taskList.add((Task) in.readObject());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Из файла " + filename + " загружено задач: " + objCnt);
        return taskList;
    }


}
