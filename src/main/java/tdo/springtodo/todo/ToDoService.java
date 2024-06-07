package tdo.springtodo.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tdo.springtodo.note.NoteModel;
import tdo.springtodo.note.NoteRepository;

import java.util.List;

@Service
public class ToDoService {
    private final ToDoRepository toDoRepository;
    private final NoteRepository noteRepository;

    @Autowired
    public ToDoService(ToDoRepository toDoRepository, NoteRepository noteRepository) {
        this.toDoRepository = toDoRepository;
        this.noteRepository = noteRepository;
    }

    public List<ToDoModel> getTasks(){
        return toDoRepository.findAll();
    }

    public Long count(){
        return toDoRepository.count();
    }

    public void addNewTask(ToDoModel ToDoModel){
        toDoRepository.save(ToDoModel);
    }

    public void addNewTask(String title, boolean done) {
        long taskNum = toDoRepository.count() + 1;
        ToDoModel toDoModel = new ToDoModel(taskNum, title, done);
        toDoRepository.save(toDoModel);
    }

    public void deleteTask(Long taskId){
        boolean exists =  toDoRepository.existsById(taskId);
        if(!exists){
            throw new IllegalStateException("Task with id" + taskId + "does not exists");
        }
        toDoRepository.deleteById(taskId);
    }

    public ToDoModel getTaskById(Long id) {
        return toDoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid task Id:" + id));
    }

    public void updateTask(Long id, String title, boolean done) {
        ToDoModel taskToUpdate = toDoRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Task with id " + id + " does not exist"));

        taskToUpdate.setTitle(title);
        taskToUpdate.setDone(done);

        toDoRepository.save(taskToUpdate);
    }

    // NOTES
    public void addNewNote(Long taskId, String content) {
        ToDoModel todo = toDoRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid task Id:" + taskId));

        NoteModel noteModel = new NoteModel(content, todo);
        noteRepository.save(noteModel);
        todo.getNotes().add(noteModel);
        toDoRepository.save(todo);
    }
}
