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

        public static Status getStatusByName(String name) {
            for (Status st : values()) {
                // либо equalsIgnoreCase, на ваше усмотрение
                if (st.getRusTitle().equals(name)) {
                    return st;
                }
            }

            // Либо просто вернуть null
            throw new IllegalArgumentException("No enum found with url: [" + name + "]");
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

    public String getOwnerName() {
        return ownerName;
    }

    public String getExecutorName() {
        return executorName;
    }

    public String getDescription() {
        return description;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setExecutorName(String executorName) {
        this.executorName = executorName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    String taskInfo() {
        return String.format("Task [id=%d, title='%s', ownerName=%s, executorName=%s, description='%s', status=%s]" ,id, title, ownerName, executorName, description, status.getRusTitle());
    }
}
