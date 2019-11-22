package com.ert.java.training.tasktracker;

import java.sql.*;
import java.util.ArrayList;

public class DbTaskRepository implements TaskRepository {
    private static Connection connection;
    private static Statement stmt;
    private static PreparedStatement prst;

    private static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:tasks.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            stmt = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void disconnect() {
        try {
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            prst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    DbTaskRepository() {
        createTable();
    }

    private void createTable() {
        connect();

        try {
            stmt.execute("CREATE TABLE IF NOT EXISTS tasks (\n" +
                            "    task_id PRIMARY KEY,\n" +
                            "    title NOT NULL,\n" +
                            "    owner_name,\n" +
                            "    executor_name,\n" +
                            "    description,\n" +
                            "    status\n" +
                              ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @Override
    public Task findById(Long id) {
        if(selectData(id, null, null).size() != 0) {
            return selectData(id, null, null).get(0);
        }
        return null;
    }

    @Override
    public boolean createTask(Task newTask) {
        try {
            prst = connection.prepareStatement("insert into tasks(task_id, title, owner_name, executor_name, description, status) values(?,?,?,?,?,?)");
            prst.setObject(1, newTask.getId());
            prst.setObject(2, newTask.getTitle());
            prst.setObject(3, newTask.getOwnerName());
            prst.setObject(4, newTask.getExecutorName());
            prst.setObject(5, newTask.getDescription());
            prst.setObject(6, newTask.getStatus().getRusTitle());
            prst.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    @Override
    public Task updateTask(Task task) {
        try {
            prst = connection.prepareStatement("update tasks set title = ?" +
                    ", owner_name = ?" +
                    ", executor_name = ?" +
                    ", description = ?" +
                    ", status = ?" +
                    "where task_id = ?");
            prst.setObject(1, task.getTitle());
            prst.setObject(2, task.getOwnerName());
            prst.setObject(3, task.getExecutorName());
            prst.setObject(4, task.getDescription());
            prst.setObject(5, task.getStatus().getRusTitle());
            prst.setObject(6, task.getId());
            prst.executeUpdate();
            return task;
        } catch (SQLException e) {
            return null;
        }
    }

    private ArrayList<Task> selectData(Long id, Task.Status status, String orderBy) {
        String sql = "select * from tasks where 1=1";
        if (id != null) {
            sql += " and task_id = ?";
        }
        if (status != null) {
            sql += " and status = ?";
        }
        if(orderBy == "status") {
            sql += " order by status";
        } else {
            sql += " order by 1";
        }
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            prst = connection.prepareStatement(sql);
            int paramIdx = 0;
            if (id != null) {
                prst.setObject(++paramIdx, id);
            }
            if (status != null) {
                prst.setObject(++paramIdx, status.getRusTitle());
            }
            ResultSet rs = prst.executeQuery();
            while(rs.next()) {
                Task task = new Task(rs.getLong("task_id"),
                        rs.getString("title"),
                        rs.getString("owner_name"),
                        rs.getString("executor_name"),
                        rs.getString("description"));
                task.setStatus(Task.Status.getStatusByName(rs.getString(6)));
                taskList.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return taskList;
    }

    @Override
    public boolean deleteTaskById(Long id) {
        try {
            prst = connection.prepareStatement("delete from tasks where task_id = ?");
            prst.setObject(1, id);
            return prst.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList<Task> getAll() {
            return selectData(null, null, null);
    }

    @Override
    public ArrayList<Task> getTasksByStatus(Task.Status status) {
        return selectData(null, status, null);
    }

    @Override
    public void setTaskStatus(Long id, Task.Status status) {
        try {
            prst = connection.prepareStatement("update tasks set status = ?" +
                    "where task_id = ?");
            prst.setObject(1, status.getRusTitle());
            prst.setObject(2, id);
            prst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean taskIdExists(Long id) {
        return selectData(id, null, null).size() == 1;
    }

    @Override
    public ArrayList<Task> getTasksSortedByStatus() {
        return selectData(null, null, "status");
    }

    @Override
    public long countTasksByStatus(Task.Status status) {
        return selectData(null, status, null).size();
    }
}