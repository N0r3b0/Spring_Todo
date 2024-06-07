package tdo.springtodo.todo;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import tdo.springtodo.SubTask.SubTaskModel;
import tdo.springtodo.note.NoteModel;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tasks")
public class ToDoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Nonnull
    private String title;

    private boolean done;

    @OneToMany(mappedBy = "todoModel", cascade = CascadeType.ALL)
    private List<SubTaskModel> subTasks;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<NoteModel> notes = new ArrayList<>();


    public ToDoModel() {

    }

    public ToDoModel(Long id, @Nonnull String title, boolean done) {
        this.id = id;
        this.title = title;
        this.done = done;
    }

    public ToDoModel(String title, boolean done) {
        this.title = title;
        this.done = done;
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

    public List<SubTaskModel> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(List<SubTaskModel> subTasks) {
        this.subTasks = subTasks;
    }

    public List<NoteModel> getNotes() {
        return notes;
    }

    public void setNotes(List<NoteModel> notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "ToDoModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", done=" + done +
                '}';
    }
}
