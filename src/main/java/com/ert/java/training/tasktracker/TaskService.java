package com.ert.java.training.tasktracker;

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


}
