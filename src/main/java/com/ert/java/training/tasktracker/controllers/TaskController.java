package com.ert.java.training.tasktracker.controllers;

import com.ert.java.training.tasktracker.entities.Task;
import com.ert.java.training.tasktracker.repositories.HibernateTaskRepository;
import com.ert.java.training.tasktracker.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TaskController {
    @Autowired
    private HibernateTaskRepository hibernateTaskRepository;

    @GetMapping("/tasks")
    @ResponseBody
    public Task taskIdExists() {
        return hibernateTaskRepository.getTaskById(1L);
    }
}
