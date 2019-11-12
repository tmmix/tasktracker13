package com.ert.java.training.tasktracker;

import java.util.ArrayList;
import java.util.Iterator;

public class ArrayTaskRepository implements TaskRepository{
    private ArrayList<Task> taskArray;

    public ArrayTaskRepository() {

        taskArray = new ArrayList<>();
        taskArray.add(new Task(1L, "Задание Один", "Белов А.А.", "Голубикин В.В.", "Выполнить задание Один"));
        taskArray.add(new Task(7L, "Задание Семь", "Рогов П.П.", "Белов А.А.", "Выполнить задание Семь"));

    }

    public boolean idExists(Long newId) {
        for (Task task : taskArray) {
            if (task != null) {
                return task.getId().equals(newId);
            }
        }
        return false;
    }

    @Override
    public Task findById(Long id) {
        for (Task task : taskArray) {
            if (task != null && task.getId().equals(id)) {
                return task;
            }
        }
        return null;
    }

    @Override
    public boolean createTask(Task newTask) {
        if (!idExists(newTask.getId())) {
            taskArray.add(newTask);
            return true;
        }
        return false;
    }

    /*@Override
    public Task updateTask(Task taskForUpdate) {
        Iterator<Task> iter = taskArray.iterator();
        while (iter.hasNext()) {
            if (iter.next().getId().equals(taskForUpdate.getId())) {
                iter.remove();
                taskArray.add(taskForUpdate);
                return taskForUpdate;
            }
        }
        return null;
    }*/

    @Override
    public Task updateTask(Task taskForUpdate) {
        for (int i = 0; i < taskArray.size(); i++) {
            if (taskArray.get(i).getId().equals(taskForUpdate.getId())) {
                taskArray.set(i, taskForUpdate);
                return taskForUpdate;
            }
        }
        return null;
    }

    @Override
    public boolean deleteTaskById(Long id) {
        Iterator<Task> iter = taskArray.iterator();
        while (iter.hasNext()) {
            if (iter.next().getId().equals(id)) {
                iter.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public ArrayList<Task> getAll() {
        return taskArray;
    }





}
