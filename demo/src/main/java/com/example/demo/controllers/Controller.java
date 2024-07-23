package com.example.demo.controllers;

import com.example.demo.Services.TodoService;
import com.example.demo.models.TodoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class Controller {

    @Autowired
    TodoService todoService;

    @PostMapping
    public int createTodo(@RequestBody TodoModel user) {
        return todoService.saveTodo(user);
    }

    // Get a user by ID
    @GetMapping("/{id}")
    public TodoModel getTodoById(@PathVariable int id) {
        return todoService.getTodoById(id);
    }

    // Get all users
    @GetMapping
    public List<TodoModel> getAllTodos() {
        return todoService.getAllTodos();
    }

    // Update a user
    @PutMapping("/{id}")
    public int updateTodo(@PathVariable int id, @RequestBody TodoModel user) {
        user.setId(id);
        return todoService.saveTodo(user);
    }

    // Delete a user
    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable int id) {
        todoService.deleteTodo(id);
    }
}

