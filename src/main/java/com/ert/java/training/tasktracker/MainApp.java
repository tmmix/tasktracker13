package com.ert.java.training.tasktracker;

public class MainApp {
    public static void main(String[] args) {
        TaskRepository allTasks = new ArrayTaskRepository();
        TaskService taskService = new TaskService(allTasks);
        try {
            taskService.addTask(new Task(4L, "Еще одно задание", "Камский Е.К.", "Журов К.В.", "Организовать поставку"));
        } catch (TaskException e){
            System.out.println("Поймали ошибку: " + e.getMessage());
        }
        try {
            taskService.addTask(new Task(4L, "Еще одно задание", "Камский Е.К.", "Журов К.В.", "Организовать поставку"));
        } catch (TaskException e){
            System.out.println("Поймали ошибку: " + e.getMessage());
        }

        taskService.addTask(new Task(6L, "Купить молоко", "Камский Е.К.", "Журов К.В.", "Организовать поставку"));
        taskService.addTask(new Task(5L, "Собрать самолет", "Камский Е.К.", "Журов К.В.", "Организовать поставку"));
        taskService.addTask(new Task(8L, "Научиться новому", "Камский Е.К.", "Журов К.В.", "Организовать поставку"));
        taskService.editTask(new Task(4L, "Еще одно задание изменено", "Камский Е.К.", "Журов К.В.", "Организовать поставку"));
        taskService.printTaskList();
        taskService.deleteTask(1L);
        try {
            taskService.deleteTask(20L);
        } catch (TaskException e) {
            System.out.println("Поймали ошибку: " + e.getMessage());
        }
        taskService.printTaskList();
        System.out.println();
        taskService.setTaskStatus(8L, Task.Status.IN_PROGRESS);
        taskService.setTaskStatus(6L, Task.Status.REJECTED);
        taskService.setTaskStatus(5L, Task.Status.DONE);
        taskService.setTaskStatus(7L, Task.Status.DONE);
        taskService.printTasksByStatus(Task.Status.IN_PROGRESS);
        taskService.checkTaskById(9L);
        taskService.printTasksSortedByStatus();
        taskService.printNumberOfTasksWithStatus(Task.Status.IN_PROGRESS);
    }
}
