package com.ert.java.training.tasktracker;

import java.util.ArrayList;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        TaskRepository allTasks = new DbTaskRepository();
        TaskService taskService = new TaskService(allTasks);
        try {
            taskService.addTask(new Task(1L, "Задание Один", "Белов А.А.", "Голубикин В.В.", "Выполнить задание Один"));
            taskService.addTask(new Task(7L, "Задание Семь", "Рогов П.П.", "Белов А.А.", "Выполнить задание Семь"));
            taskService.addTask(new Task(4L, "Еще одно задание", "Камский Е.К.", "Журов К.В.", "Организовать поставку"));
            taskService.addTask(new Task(6L, "Купить молоко", "Камский Е.К.", "Журов К.В.", "Организовать поставку"));
            taskService.addTask(new Task(5L, "Собрать самолет", "Камский Е.К.", "Журов К.В.", "Организовать поставку"));
            taskService.addTask(new Task(8L, "Научиться новому", "Камский Е.К.", "Журов К.В.", "Организовать поставку"));
        } catch (TaskException e){
            System.out.println("Поймали ошибку: " + e.getMessage());
        }
        taskService.editTask(new Task(4L, "Еще одно задание изменено", "Камский Е.К.", "Журов К.В.", "Организовать поставку"));
        taskService.printTaskList();
        try {
            taskService.deleteTask(1L);
            taskService.deleteTask(20L);
        } catch (TaskException e) {
            System.out.println("Поймали ошибку: " + e.getMessage());
        }
        System.out.println();
        taskService.setTaskStatus(8L, Task.Status.IN_PROGRESS);
        taskService.setTaskStatus(6L, Task.Status.REJECTED);
        taskService.setTaskStatus(5L, Task.Status.DONE);
        taskService.setTaskStatus(7L, Task.Status.DONE);
        taskService.printTasksByStatus(Task.Status.IN_PROGRESS);
        taskService.checkTaskById(8L);
        taskService.printTasksSortedByStatus();
        taskService.printNumberOfTasksWithStatus(Task.Status.IN_PROGRESS);
        List<Task> tl = new ArrayList<>();
        tl.add(taskService.getTask(4L));
        tl.add(taskService.getTask(5L));
        tl.add(taskService.getTask(6L));
        tl.add(taskService.getTask(6L));
        taskService.saveTasks(tl, "tasks.dat");
        tl = taskService.loadTasks("tasks.dat");
        for (Task task : tl) {
            System.out.println(task.taskInfo());
        }
    }
}
