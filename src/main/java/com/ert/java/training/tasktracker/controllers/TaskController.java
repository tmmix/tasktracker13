package com.ert.java.training.tasktracker.controllers;

import com.ert.java.training.tasktracker.entities.Task;
import com.ert.java.training.tasktracker.repositories.HibernateTaskRepository;
import com.ert.java.training.tasktracker.repositories.TaskRepository;
import com.ert.java.training.tasktracker.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    private HibernateTaskRepository hibernateTaskRepository;

    @Autowired
    public void setHibernateTaskRepository(HibernateTaskRepository hibernateTaskRepository) {
        this.hibernateTaskRepository = hibernateTaskRepository;
    }

    @GetMapping("/info")
//    @ResponseBody
    public String showInfoPage(Model model, @RequestParam(value = "title", required = false) String title) {
        model.addAttribute("task", new Task(3L, title, "Owner", "Executor", "description", Task.Status.CREATED));
        return "info";
    }
}
