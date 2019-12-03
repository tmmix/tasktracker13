package com.ert.java.training.tasktracker;

import org.hibernate.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TrackerConfig.class);
        TaskService ts = context.getBean("taskService", TaskService.class);
        ts.printTaskList();
        context.close();
        /*TaskRepository allTasks = new DbTaskRepository();
        TaskService taskService = new TaskService(allTasks);
//        System.out.println(taskService.getTask(1L).taskInfo());
        try {
            taskService.addTask(new Task(4L, "Еще одно задание", "Камский Е.К.", "Журов К.В.", "Организовать поставку"));
            taskService.addTask(new Task(11L, "Одиннадцатое задание", "Камский Е.К.", "Журов К.В.", "Организовать поставку", Task.Status.CREATED));
            taskService.addTask(new Task(10L, "Задание Десять", "Перкинс А.А.", "Деловой В.В.", "Выполнить задание Десять"));
            taskService.addTask(new Task(7L, "Задание Семь", "Рогов П.П.", "Белов А.А.", "Выполнить задание Семь"));
        } catch (TaskException e){
            System.out.println("Поймали ошибку: " + e.getMessage());
        }
        taskService.printTaskList();
        taskService.editTask(new Task(4L,
                                      "Еще одно задание исправлено",
                                      "Камский Е.К. (зам)",
                                      "Журов К.В. (ИО Журова)",
                                      "Описание обновлено " + LocalDateTime.now(),
                                      Task.Status.IN_PROGRESS));
        taskService.printTaskList();
        try {
            taskService.deleteTask(20L);
        } catch (TaskException e) {
            System.out.println("Поймали ошибку: " + e.getMessage());
        }
        System.out.println();
        taskService.setTaskStatus(8L, Task.Status.IN_PROGRESS);
        taskService.setTaskStatus(6L, Task.Status.REJECTED);
        taskService.setTaskStatus(5L, Task.Status.DONE);
        taskService.setTaskStatus(7L, Task.Status.DONE);
        taskService.printTaskList();
//        taskService.printTasksByStatus(Task.Status.DONE);
        taskService.checkTaskById(7L);
        taskService.printTasksSortedByStatus();
//        taskService.printNumberOfTasksWithStatus(Task.Status.IN_PROGRESS);
//        List<Task> tl = new ArrayList<>();
//        tl.add(taskService.getTask(4L));
//        tl.add(taskService.getTask(5L));
//        tl.add(taskService.getTask(6L));
//        tl.add(taskService.getTask(6L));
//        taskService.saveTasks(tl, "tasks.dat");
//        tl = taskService.loadTasks("tasks.dat");
//        for (int i = 0; i < tl.size(); i++) {
//            System.out.println(tl.get(i).taskInfo());
//        }
         */
    }
}
