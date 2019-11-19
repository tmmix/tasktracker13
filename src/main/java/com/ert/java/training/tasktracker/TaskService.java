package com.ert.java.training.tasktracker;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TaskService {
    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void addTask(Task newTask) {
        if (taskRepository.findById(newTask.getId()) != null) {
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

    public Task getTask(Long id) {
        return taskRepository.findById(id);
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
            System.out.println(objCnt);
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
