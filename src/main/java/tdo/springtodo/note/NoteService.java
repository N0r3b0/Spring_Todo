package tdo.springtodo.note;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<NoteModel> getNotes() {
        return noteRepository.findAll();
    }

    public void addNewNote(NoteModel noteModel) {
        noteRepository.save(noteModel);
    }

    public void deleteNote(Long noteId) {
        noteRepository.deleteById(noteId);
    }

    public NoteModel getNoteById(Long id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid note Id:" + id));
    }

    public void updateNote(Long id, String content) {
        NoteModel noteToUpdate = noteRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Note with id " + id + " does not exist"));

        noteToUpdate.setContent(content);

        noteRepository.save(noteToUpdate);
    }
}
