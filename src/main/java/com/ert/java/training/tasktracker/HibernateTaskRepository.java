package com.ert.java.training.tasktracker;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HibernateTaskRepository implements TaskRepository{
    private static SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .buildSessionFactory();

    private static Session session = null;
//        try {
//            session = factory.getCurrentSession();
//            session.beginTransaction();
//            Task task = session.get(Task.class, 1L);
//            System.out.println(task.taskInfo());
//        } catch (
//            HibernateException e) {
//            e.printStackTrace();
//        } finally {
//            factory.close();
//        }

    @Override
    public Task findById(Long id) {
        Task task = new Task();
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            task = session.get(Task.class, id);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return task;
    }

    @Override
    public boolean createTask(Task newTask) {
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            session.save(newTask);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public Task updateTask(Task task) {
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            session.update(task);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return task;
    }

    @Override
    public boolean deleteTaskById(Long id) {
        Task task;
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            task = session.get(Task.class, id);
            if (task != null) {
                session.delete(task);
            } else {
                return false;
            }
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public ArrayList<Task> getAll() {
        List<Task> taskList = null;
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            taskList = session.createQuery("select s from Task s order by s.statusName", Task.class).getResultList();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return (ArrayList<Task>) taskList;
    }

    @Override
    public ArrayList<Task> getTasksByStatus(Task.Status status) {
        List<Task> taskList = null;
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            taskList = session.createQuery("select s from Task s where s.statusName = :statusName", Task.class)
                    .setParameter("statusName", status.getRusTitle())
                    .getResultList();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return (ArrayList<Task>) taskList;
    }

    @Override
    public void setTaskStatus(Long id, Task.Status status) {
        Task task;
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            task = session.get(Task.class, id);
            task.setStatus(status);
            session.update(task);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean taskIdExists(Long id) {
        return findById(id) != null;
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
