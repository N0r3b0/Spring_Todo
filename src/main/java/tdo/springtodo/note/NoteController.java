package tdo.springtodo.note;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tdo.springtodo.todo.*;

import java.util.List;

@Controller
@RequestMapping("/api")
@CrossOrigin
public class NoteController {

    private final NoteService noteService;
    private final ToDoService toDoService;

    @Autowired
    public NoteController(NoteService noteService, ToDoService toDoService) {
        this.noteService = noteService;
        this.toDoService = toDoService;
    }

    @GetMapping("/notes/{taskId}")
    public ModelAndView viewNotes(@PathVariable Long taskId) {
        ModelAndView modelAndView = new ModelAndView("notes");
        ToDoModel todo = toDoService.getTaskById(taskId);
        List<NoteModel> notes = todo.getNotes();
        modelAndView.addObject("notes", notes);
        modelAndView.addObject("taskId", taskId);
        return modelAndView;
    }

    @PostMapping("/notes/{taskId}")
    public ModelAndView addNote(@PathVariable Long taskId, @RequestParam String content) {
        ToDoModel todo = toDoService.getTaskById(taskId);
        NoteModel noteModel = new NoteModel(content, todo);
        noteService.addNewNote(noteModel);
        return new ModelAndView("redirect:/api/notes/{taskId}");
    }

    @GetMapping("/notes/{taskId}/edit/{noteId}")
    public ModelAndView editNote(@PathVariable Long taskId, @PathVariable Long noteId) {
        ModelAndView modelAndView = new ModelAndView("edit_note");
        NoteModel note = noteService.getNoteById(noteId);
        modelAndView.addObject("note", note);
        modelAndView.addObject("taskId", taskId);
        return modelAndView;
    }

    @PostMapping("/notes/{taskId}/edit/{noteId}")
    public ModelAndView updateNote(@PathVariable Long taskId, @PathVariable Long noteId, @RequestParam String content) {
        noteService.updateNote(noteId, content);
        return new ModelAndView("redirect:/api/notes/{taskId}");
    }

    @GetMapping("/notes/{taskId}/remove/{noteId}")
    public ModelAndView removeNote(@PathVariable Long taskId, @PathVariable Long noteId) {
        noteService.deleteNote(noteId);
        return new ModelAndView("redirect:/api/notes/{taskId}");
    }
}
