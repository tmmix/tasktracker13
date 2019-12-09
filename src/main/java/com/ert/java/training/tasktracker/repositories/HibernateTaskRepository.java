package com.ert.java.training.tasktracker.repositories;

import com.ert.java.training.tasktracker.entities.Task;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
@Repository
@Transactional
public class HibernateTaskRepository implements TaskRepository {
    private SessionFactory factory;

    @Autowired
    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }


    @Override
    public Task getTaskById(Long id) {
        Session session = factory.getCurrentSession();
        Task task = session.get(Task.class, id);
        return task;
    }

    @Override
    public boolean createTask(Task newTask) {
        Session session = factory.getCurrentSession();
        session.save(newTask);
        return true;
    }

    @Override
    public Task updateTask(Task task) {
        Session session = factory.getCurrentSession();
        session.update(task);
        return task;
    }

    @Override
    public boolean deleteTaskById(Long id) {
        Session session = factory.getCurrentSession();
        Task task;
        task = session.get(Task.class, id);
        if (task != null) {
            session.delete(task);
        } else {
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<Task> getAll() {
        Session session = factory.getCurrentSession();
        List<Task> taskList = Collections.emptyList();
        taskList = session.createQuery("select s from Task s", Task.class).getResultList();
        return (ArrayList<Task>) taskList;
    }

    @Override
    public ArrayList<Task> getTasksByStatus(Task.Status status) {
        Session session = factory.getCurrentSession();
        List<Task> taskList = null;
        taskList = session.createQuery("select s from Task s where s.statusName = :statusName", Task.class)
                 .setParameter("statusName", status.getRusTitle())
                 .getResultList();
        return (ArrayList<Task>) taskList;
    }

    @Override
    public void setTaskStatus(Long id, Task.Status status) {
        Session session = factory.getCurrentSession();
        Task task;
        task = session.get(Task.class, id);
        task.setStatus(status);
        session.update(task);
    }

    @Override
    public boolean taskIdExists(Long id) {
        return getTaskById(id) != null;
    }

    @Override
    public ArrayList<Task> getTasksSortedByStatus() {
        ArrayList<Task> taskList = getAll();
        taskList.sort(Comparator.comparing(Task::getStatusName));
        return taskList;
    }

    @Override
    public long countTasksByStatus(Task.Status status) {
        return getTasksByStatus(status).size();
    }
}
