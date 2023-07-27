package net.kompheak.tomanagement.service;

import net.kompheak.tomanagement.dto.ToDoDto;

import java.util.List;

public interface ToDoService {
    ToDoDto addToDo(ToDoDto toDoDto);

    ToDoDto getTodo(Long id);

    List<ToDoDto> getAllToDo();

    ToDoDto updateTodo(ToDoDto toDoDto, Long id);

    void deleteTodo(Long id);
}
