package com.ert.java.training.tasktracker;

import java.io.Serializable;

class Task implements Serializable {

    public enum Status {
        CREATED("Создана", 1), IN_PROGRESS("В работе", 2), DONE("Выполнена", 3), REJECTED("Отклонена", 4);

        private String rusTitle;
        private int orderNum;

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

    private Long id;
    private String title;
    private String ownerName;
    private String executorName;
    private String description;
    private Status status;

    Task(Long id, String title, String ownerName, String executorName, String description) {
        this.id = id;
        this.title = title;
        this.ownerName = ownerName;
        this.executorName = executorName;
        this.description = description;
        this.status = Status.CREATED;
    }

    Long getId() {
        return id;
    }

    String getTitle() {
        return title;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    String taskInfo() {
        return String.format("Task [id=%d, title='%s', ownerName=%s, executorName=%s, description='%s', status=%s]" ,id, title, ownerName, executorName, description, status.getRusTitle());
    }
}
