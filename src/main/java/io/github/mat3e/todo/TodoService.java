package io.github.mat3e.todo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TodoService {
    private final Logger logger = LoggerFactory.getLogger(TodoServlet.class);
    TodoRepository repository;

    TodoService(TodoRepository repository){
        this.repository = repository;
    }

    List<Todo> findAll(){
      return repository.findAll();
    }

    Optional<Todo> toggleTodo(Integer id){
        var todo = repository.findById(id);
        todo.ifPresent(t -> {
            t.setDone(!t.isDone());
            repository.save(t);
        });
        return todo;
    }

    Todo addTodo(Todo newTodo){
        repository.save(newTodo);
        return newTodo;
    }

    List<Todo> deleteTodo(){
        List<Todo> todos;
        todos = repository.findAll().stream()
                .filter(t -> t.isDone()).collect(Collectors.toList());
        List<Todo> toReturn = todos;
        repository.deleteAll(todos);
        return toReturn;
    }

}
