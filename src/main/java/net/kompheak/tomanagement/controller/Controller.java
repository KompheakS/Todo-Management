package net.kompheak.tomanagement.controller;

import lombok.AllArgsConstructor;
import net.kompheak.tomanagement.dto.ToDoDto;
import net.kompheak.tomanagement.service.ToDoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/todos")
@AllArgsConstructor
public class Controller {
    private ToDoService toDoService;

    @PostMapping()
    public ResponseEntity<?> addTodo(@RequestBody ToDoDto toDoDto){
        ToDoDto savedTodo = toDoService.addToDo(toDoDto);

        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTodo(@PathVariable("id") Long id){
        ToDoDto toDoDto = toDoService.getTodo(id);

        return ResponseEntity.ok(toDoDto);
    }

    @GetMapping()
    public ResponseEntity<List<?>> getAllTodo(){

        return ResponseEntity.ok(toDoService.getAllToDo());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTodo(@RequestBody ToDoDto toDoDto,@PathVariable("id") Long id){
        ToDoDto updatedTodo = toDoService.updateTodo(toDoDto, id);

        return ResponseEntity.ok(updatedTodo);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable("id") Long id){
        toDoService.deleteTodo(id);

        return ResponseEntity.ok("Todo has been delete successfully!");
    }
}
