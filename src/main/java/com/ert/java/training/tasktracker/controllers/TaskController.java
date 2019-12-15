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
    private TaskService taskService;

    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/info")
    @ResponseBody
    public List<Task> showInfoPage() {
        return taskService.getTaskList();
    }

    @GetMapping("/taskinfo")
    public String showTaskPage(Model model, @RequestParam(value = "taskid", required = true) Long taskId) {
        model.addAttribute("task", taskService.getTask(taskId));
        return "taskinfo";
    }
}
