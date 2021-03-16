package io.github.mat3e.todo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

/**
 * Handling of languages
 */
@RestController
@RequestMapping("/api/todos")
class TodoServlet {
    private final Logger logger = LoggerFactory.getLogger(TodoServlet.class);
    private TodoService todoService;

    TodoServlet(TodoService todoService){
        this.todoService = todoService;

    }
    @GetMapping
    ResponseEntity<List<Todo>> findAllTodos(){
        logger.info("Lang request got");
        return ResponseEntity.ok(todoService.findAll());
    }

    @PutMapping("/{id}")
    ResponseEntity<Todo> toggleTodo(@PathVariable Integer id){
        Optional<Todo> todo = todoService.toggleTodo(id);
        return todo.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    ResponseEntity<Todo> addTodo(@RequestBody Todo todo){
        return ResponseEntity.ok(todoService.addTodo(todo));
    }

    @DeleteMapping
    ResponseEntity<List<Todo>> deleteTodo(){
        return ResponseEntity.ok(todoService.deleteTodo());
    }
}

