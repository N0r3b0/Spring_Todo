package tdo.springtodo.SubTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tdo.springtodo.note.NoteModel;
import tdo.springtodo.note.NoteRepository;

import java.util.List;

@Service
public class SubTaskService {

    private final SubTaskRepository subTaskRepository;
    private final NoteRepository noteRepository;

    @Autowired
    public SubTaskService(SubTaskRepository subTaskRepository, NoteRepository noteRepository) {
        this.subTaskRepository = subTaskRepository;
        this.noteRepository = noteRepository;
    }

    public List<SubTaskModel> getSubTasks() {
        return subTaskRepository.findAll();
    }

    public void addNewSubTask(SubTaskModel subTaskModel) {
        subTaskRepository.save(subTaskModel);
    }

    public void deleteSubTask(Long subTaskId) {
        subTaskRepository.deleteById(subTaskId);
    }

    public SubTaskModel getSubTaskById(Long id) {
        return subTaskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid subtask Id:" + id));
    }

    public void updateSubTask(Long id, String title, boolean done) {
        SubTaskModel subTaskToUpdate = subTaskRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Subtask with id " + id + " does not exist"));

        subTaskToUpdate.setTitle(title);
        subTaskToUpdate.setDone(done);

        subTaskRepository.save(subTaskToUpdate);
    }

    public void addNewNote(Long subTaskId, String content) {
        SubTaskModel subTask = subTaskRepository.findById(subTaskId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid subtask Id:" + subTaskId));

        NoteModel noteModel = new NoteModel(content, subTask);
        noteRepository.save(noteModel);
        subTask.getNotes().add(noteModel);
        subTaskRepository.save(subTask);
    }
}
