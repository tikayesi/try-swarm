package com.example.simpletodo.service;

import com.example.simpletodo.entity.ToDo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ToDoService {
    public ToDo createTodo(ToDo toDo);
    List<ToDo> getTodolist();

    Page getTodoPerPage(Pageable pageable);
    ToDo getDetail(String id);

    ToDo updateTodo(ToDo toDo);
    void deleteTodo(String id);
}
