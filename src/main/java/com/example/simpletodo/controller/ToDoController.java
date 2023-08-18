package com.example.simpletodo.controller;

import com.example.simpletodo.entity.ToDo;
import com.example.simpletodo.service.ToDoService;
import com.example.simpletodo.utils.customResponse.PageResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ToDoController {
    ToDoService toDoService;

    @Autowired
    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @PostMapping("/todos")
    public ToDo save(@RequestBody ToDo toDo){
        return toDoService.createTodo(toDo);
    }

    @GetMapping("/todos")
    public List<ToDo> getList(){
        return toDoService.getTodolist();
    }

    @GetMapping("/todos/perpage")
    public PageResponseWrapper<ToDo> getTodoPerpage(
            @RequestParam(name = "page", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "size", defaultValue = "5") Integer size,
            @RequestParam(name = "sort-by", defaultValue = "todo")String sortBy,
            @RequestParam(name = "direction", defaultValue = "ASC") String direction
            ){
        Sort sorting = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(pageNumber, size, sorting);
        Page<ToDo> page = toDoService.getTodoPerPage(pageable);
        return new PageResponseWrapper<>(page);
    }

    @GetMapping("/todos/{id}")
    public ToDo getDetail(@PathVariable String id){
        return toDoService.getDetail(id);
    }

    @PutMapping("/todos")
    public ToDo updateTodo(@RequestBody ToDo toDo){
        return toDoService.updateTodo(toDo);
    }

    @DeleteMapping("/todos/{id}")
    public void DeleteTodo(@PathVariable String id){
         toDoService.deleteTodo(id);
    }
}
