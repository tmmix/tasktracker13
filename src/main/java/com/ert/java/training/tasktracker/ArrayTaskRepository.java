package com.ert.java.training.tasktracker;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;

public class ArrayTaskRepository implements TaskRepository{
    private ArrayList<Task> taskArray;

    public ArrayTaskRepository() {
       taskArray = new ArrayList<>();
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

    @Override
    public ArrayList<Task> getTasksByStatus(Task.Status status) {
        return (ArrayList<Task>) taskArray.stream().filter(t -> t.getStatus() == status).collect(Collectors.toList());
    }

    @Override
    public void setTaskStatus(Long id, Task.Status status) {
        findById(id).setStatus(status);
    }

    @Override
    public boolean taskIdExists(Long id) {
        return taskArray.stream().anyMatch(task -> task.getId() == id);
    }

    @Override
    public ArrayList<Task> getTasksSortedByStatus() {
        return (ArrayList<Task>) taskArray.stream().sorted((o1, o2) -> o1.getStatus().getOrderNum() - o2.getStatus().getOrderNum()).collect(Collectors.toList());
    }

    @Override
    public long countTasksByStatus(Task.Status status) {
        return taskArray.stream().filter(task -> task.getStatus() == status).count();
    }
}
