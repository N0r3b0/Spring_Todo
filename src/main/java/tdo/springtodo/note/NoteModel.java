package tdo.springtodo.note;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import tdo.springtodo.SubTask.SubTaskModel;
import tdo.springtodo.todo.*;

@Entity
@Table(name = "notes")
public class NoteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Nonnull
    private String content;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private ToDoModel task;

    @ManyToOne
    @JoinColumn(name = "sub_task_id") // Assuming you have a foreign key column named "sub_task_id" in NoteModel
    private SubTaskModel subTask;

    public NoteModel() {

    }

    public NoteModel(Long id, @Nonnull String content, ToDoModel task, SubTaskModel subtask) {
        this.id = id;
        this.content = content;
        this.task = task;
        this.subTask = subtask;
    }

    public NoteModel(String content, ToDoModel task) {
        this.content = content;
        this.task = task;
    }

    public NoteModel(String content, SubTaskModel subtask) {
        this.content = content;
        this.subTask = subtask;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Nonnull
    public String getContent() {
        return content;
    }

    public void setContent(@Nonnull String content) {
        this.content = content;
    }

    public ToDoModel getTask() {
        return task;
    }

    public void setTask(ToDoModel task) {
        this.task = task;
    }

    public SubTaskModel getSubtask() {
        return subTask;
    }

    public void setSubtask(SubTaskModel subtask) {
        this.subTask = subtask;
    }

    @Override
    public String toString() {
        return "NoteModel{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}
