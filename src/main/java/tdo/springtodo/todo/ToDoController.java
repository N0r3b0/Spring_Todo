package tdo.springtodo.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ToDoController {
    private final ToDoService toDoService;
    @Autowired
    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @GetMapping("/index")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("ToDoModels", toDoService.getTasks());
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView create(){
        return new ModelAndView("create");
    }

    @GetMapping("/modify/{id}")
    public ModelAndView modifyTask(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("modify");
        ToDoModel task = toDoService.getTaskById(id);
        modelAndView.addObject("task", task);
        return modelAndView;
    }

    @PostMapping("/modify/{id}")
    public ModelAndView updateTask(@PathVariable Long id, @ModelAttribute("task") ToDoModel task) {
        task.setId(id);
        toDoService.addNewTask(task);
        return new ModelAndView("redirect:/api/index");
    }

    @PostMapping("/newtask")
    public ModelAndView addTask(@RequestParam String title, @RequestParam(required = false, defaultValue = "false") boolean done) {
        toDoService.addNewTask(title, done);
        return new ModelAndView("redirect:/api/index");
    }

    @GetMapping("/delete/{taskId}")
    public RedirectView deleteTask(@PathVariable("taskId") Long taskId){
        toDoService.deleteTask(taskId);
        return new RedirectView("redirect:/api/index");

    }

    @PostMapping("/update/{taskId}")
    public RedirectView updateTask(@PathVariable("taskId") Long taskId, @RequestParam String title, @RequestParam(required = false) boolean done) {
        toDoService.updateTask(taskId, title, done);
        return new RedirectView("/api/index");
    }

    @RequestMapping("books_list")
    public String viewTasksList(Model model){
        List<ToDoModel> tasks = toDoService.getTasks();
        model.addAttribute("tasks", tasks);
        return "tasks_list";
    }

}
