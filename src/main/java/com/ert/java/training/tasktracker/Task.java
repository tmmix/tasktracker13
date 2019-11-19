package com.ert.java.training.tasktracker;

import java.io.Serializable;

class Task implements Serializable {

    public enum Status {
        CREATED("Создана"), IN_PROGRESS("В работе"), DONE("Выполнена"), REJECTED("Отклонена");

        private String rusTitle;

        public String getRusTitle() {
            return rusTitle;
        }

        Status(String rusTitle) {
            this.rusTitle = rusTitle;
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

    String taskInfo() {
        return String.format("Task [id=%d, title='%s', ownerName=%s, executorName=%s, description='%s', status=%s]" ,id, title, ownerName, executorName, description, status.getRusTitle());
    }
}
