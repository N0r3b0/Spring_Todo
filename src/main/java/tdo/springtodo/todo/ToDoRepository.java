package tdo.springtodo.todo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoRepository extends JpaRepository<ToDoModel, Long> {

}
