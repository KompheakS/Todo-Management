package net.kompheak.tomanagement.service.impl;

import lombok.AllArgsConstructor;
import net.kompheak.tomanagement.dto.ToDoDto;
import net.kompheak.tomanagement.entity.Todo;
import net.kompheak.tomanagement.exception.ResourceNotFoundException;
import net.kompheak.tomanagement.repository.TodoRepository;
import net.kompheak.tomanagement.service.ToDoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements ToDoService {

    private ModelMapper modelMapper;

    private TodoRepository todoRepository;
    @Override
    public ToDoDto addToDo(ToDoDto toDoDto) {
        //convert from dto to todo object
        /*Todo todo = new Todo();
        todo.setTitle(toDoDto.getTitle());
        todo.setDescription(toDoDto.getTitle());
        todo.setCompleted(toDoDto.isCompleted());*/

        //mapping todo with todoDto with mapper
        Todo todo = modelMapper.map(toDoDto, Todo.class);

        //saved jpa entity to the database
        Todo savedTodo = todoRepository.save(todo);

        //convert to todoDTO
        /*ToDoDto savedToDoDto = new ToDoDto();
        savedToDoDto.setId(savedTodo.getId());
        savedToDoDto.setTitle(savedTodo.getTitle());
        savedToDoDto.setDescription(savedTodo.getTitle());
        savedToDoDto.setCompleted(savedTodo.isCompleted());*/

        //return dto with using mapper class
        return modelMapper.map(savedTodo, ToDoDto.class);
    }

    @Override
    public ToDoDto getTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: "+id));

        return modelMapper.map(todo, ToDoDto.class);
    }

    @Override
    public List<ToDoDto> getAllToDo() {
        List<Todo> todoList = todoRepository.findAll();
        return todoList.stream()
                .map((todo) -> modelMapper.map(todo, ToDoDto.class))
                        .collect(Collectors.toList());
    }

    @Override
    public ToDoDto updateTodo(ToDoDto toDoDto, Long id) {
        Todo findTodo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: "+id));

        findTodo.setTitle(toDoDto.getTitle());
        findTodo.setDescription(toDoDto.getDescription());
        findTodo.setCompleted(toDoDto.isCompleted());

        return modelMapper.map(todoRepository.save(findTodo), ToDoDto.class);
    }

    @Override
    public void deleteTodo(Long id) {
        todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: "+id));

        todoRepository.deleteById(id);
    }
}
