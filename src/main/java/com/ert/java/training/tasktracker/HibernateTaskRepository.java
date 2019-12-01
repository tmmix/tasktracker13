package com.ert.java.training.tasktracker;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
@Repository
public class HibernateTaskRepository implements TaskRepository{
    private static SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .buildSessionFactory();

    private static Session session = null;

    @Override
    public Task getTaskById(Long id) {
        Task task = new Task();
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            task = session.get(Task.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.getTransaction().commit();

        }
        return task;
    }

    @Override
    public boolean createTask(Task newTask) {
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            session.save(newTask);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.getTransaction().commit();

        }
        return true;
    }

    @Override
    public Task updateTask(Task task) {
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            session.update(task);

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.getTransaction().commit();
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

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.getTransaction().commit();
        }
        return true;
    }

    @Override
    public ArrayList<Task> getAll() {
        List<Task> taskList = null;
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            taskList = session.createQuery("select s from Task s", Task.class).getResultList();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.getTransaction().commit();
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
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.getTransaction().commit();
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
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.getTransaction().commit();
        }
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
