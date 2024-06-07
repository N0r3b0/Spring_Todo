package tdo.springtodo.SubTask;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import tdo.springtodo.note.NoteModel;
import tdo.springtodo.todo.ToDoModel;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "subtasks")
public class SubTaskModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Nonnull
    private String title;

    private boolean done;

    @ManyToOne
    @JoinColumn(name = "todo_id")
    private ToDoModel todoModel;

    public SubTaskModel() {

    }

    @OneToMany(mappedBy = "subTask", cascade = CascadeType.ALL)
    private List<NoteModel> notes = new ArrayList<>();

    public SubTaskModel(Long id, @Nonnull String title, boolean done, ToDoModel todoModel) {
        this.id = id;
        this.title = title;
        this.done = done;
        this.todoModel = todoModel;
    }

    public SubTaskModel(String title, boolean done, ToDoModel todoModel) {
        this.title = title;
        this.done = done;
        this.todoModel = todoModel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Nonnull
    public String getTitle() {
        return title;
    }

    public void setTitle(@Nonnull String title) {
        this.title = title;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public ToDoModel getTodoModel() {
        return todoModel;
    }

    public void setTodoModel(ToDoModel todoModel) {
        this.todoModel = todoModel;
    }

    public List<NoteModel> getNotes() {
        return notes;
    }

    public void setNotes(List<NoteModel> notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "SubTaskModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", done=" + done +
                '}';
    }
}
