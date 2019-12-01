package com.ert.java.training.tasktracker;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tasks")
class Task implements Serializable {

    public enum Status {
        CREATED("Создана", 1), IN_PROGRESS("В работе", 2), DONE("Выполнена", 3), REJECTED("Отклонена", 4);

        private String rusTitle;
        private int orderNum;

        public static Status getStatusByName(String string) {
            for (Status s: values()) {
                if(s.getRusTitle().equals(string)){
                    return s;
                }
            }
            throw new IllegalArgumentException("Не найден enum статуса с именем: [" + string + "]");
        }

        public String getRusTitle() {
            return rusTitle;
        }

        public int getOrderNum() {
            return orderNum;
        }

        Status(String rusTitle, int orderNum) {
            this.rusTitle = rusTitle;
            this.orderNum = orderNum;
        }
    }

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;
    @Column(name = "owner_name")
    private String ownerName;
    @Column(name = "executor_name")
    private String executorName;
    @Column(name = "description")
    private String description;
    @Column(name = "status_name")
    private String statusName;
    @Column(name = "status")
    private Status status;

    public Task() {
    }

    public Task(Long id, String title, String ownerName, String executorName, String description, Task.Status status) {
        this.id = id;
        this.title = title;
        this.ownerName = ownerName;
        this.executorName = executorName;
        this.description = description;
        this.status = Status.CREATED;
        this.statusName = status.getRusTitle();
    }

    public Task(Long id, String title, String ownerName, String executorName, String description) {
        this(id, title, ownerName, executorName, description, Status.CREATED);
    }

    Long getId() {
        return id;
    }

    String getTitle() {
        return title;
    }

    public void setStatus(Status status) {
        this.status = status;
        this.statusName = status.getRusTitle();
    }

    public void setStatus(String statusName) {
        this.status = Status.getStatusByName(statusName);
        this.statusName = status.getRusTitle();
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getExecutorName() {
        return executorName;
    }

    public String getDescription() {
        return description;
    }


    public Status getStatus() {
        return status;
    }

    public String getStatusName() {
        return statusName;
    }

    String taskInfo() {
        return String.format("Task [id=%d, title='%s', ownerName=%s, executorName=%s, description='%s', status_name=%s]" ,id, title, ownerName, executorName, description, statusName);
    }
}
