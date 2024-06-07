package tdo.springtodo.SubTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import tdo.springtodo.todo.*;

import java.util.List;

@Controller
@RequestMapping("/api")
@CrossOrigin
public class SubTaskController {

    private final SubTaskService subTaskService;
    private final ToDoService toDoService;

    @Autowired
    public SubTaskController(SubTaskService subTaskService, ToDoService toDoService) {
        this.subTaskService = subTaskService;
        this.toDoService = toDoService;
    }

    @GetMapping("/subtasks/{taskId}")
    public ModelAndView viewSubTasks(@PathVariable Long taskId) {
        ModelAndView modelAndView = new ModelAndView("subtasks");
        ToDoModel todo = toDoService.getTaskById(taskId);
        List<SubTaskModel> subTasks = todo.getSubTasks();
        modelAndView.addObject("subTasks", subTasks);
        modelAndView.addObject("taskId", taskId);
        modelAndView.addObject("notes", todo.getNotes()); // Add notes to the model
        return modelAndView;
    }

    @PostMapping("/subtasks/{taskId}")
    public ModelAndView addSubTask(@PathVariable Long taskId, @RequestParam String title, @RequestParam(required = false, defaultValue = "false") boolean done) {
        ToDoModel todo = toDoService.getTaskById(taskId);
        SubTaskModel subTaskModel = new SubTaskModel(title, done, todo);
        subTaskService.addNewSubTask(subTaskModel);
        return new ModelAndView("redirect:/api/subtasks/{taskId}");
    }

    @GetMapping("/subtasks/{taskId}/edit/{subTaskId}")
    public ModelAndView editSubTask(@PathVariable Long taskId, @PathVariable Long subTaskId) {
        ModelAndView modelAndView = new ModelAndView("edit_subtask");
        SubTaskModel subTask = subTaskService.getSubTaskById(subTaskId);
        modelAndView.addObject("subTask", subTask);
        modelAndView.addObject("taskId", taskId);
        return modelAndView;
    }

    @PostMapping("/subtasks/{taskId}/edit/{subTaskId}")
    public ModelAndView updateSubTask(@PathVariable Long taskId, @PathVariable Long subTaskId, @RequestParam String title, @RequestParam(required = false, defaultValue = "false") boolean done) {
        SubTaskModel subTask = subTaskService.getSubTaskById(subTaskId);
        subTask.setTitle(title);
        subTask.setDone(done);
        subTaskService.addNewSubTask(subTask);
        return new ModelAndView("redirect:/api/subtasks/{taskId}");
    }

    @GetMapping("/subtasks/{taskId}/remove/{subTaskId}")
    public ModelAndView removeSubTask(@PathVariable Long taskId, @PathVariable Long subTaskId) {
        subTaskService.deleteSubTask(subTaskId);
        return new ModelAndView("redirect:/api/subtasks/{taskId}");
    }

}
