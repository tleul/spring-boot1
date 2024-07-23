package com.example.demo.Services;

import com.example.demo.models.TodoModel;
import com.example.demo.producer.Producer;
import com.example.demo.repositories.TodoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepo todoRepo;
    @Autowired
    private Producer producer;

    public int saveTodo(TodoModel todo) {
        //send message to broker //todos topic

        producer.sendMessage(todo.toString());

        return todoRepo.save(todo);
    }

    public TodoModel getTodoById(int id) {
        return todoRepo.findById(id);
    }

    public List<TodoModel> getAllTodos() {
        return todoRepo.findAll();
    }

    public void deleteTodo(int id) {
        todoRepo.deleteById(id);
    }
}

