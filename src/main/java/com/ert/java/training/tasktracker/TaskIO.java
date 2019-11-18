package com.ert.java.training.tasktracker;

import java.io.Serializable;
import java.util.List;

public class TaskIO implements Serializable {
    public boolean ExportToFile(List<Task> tasks, String filename) {
        return true;
    }
}
