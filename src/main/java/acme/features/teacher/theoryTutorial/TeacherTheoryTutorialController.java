package acme.features.teacher.theoryTutorial;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.TheoryTutorial;
import acme.framework.controllers.AbstractController;
import acme.roles.Teacher;

@Controller
public class TeacherTheoryTutorialController extends AbstractController<Teacher, TheoryTutorial> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected TeacherTheoryTutorialListService listService;

	@Autowired
	protected TeacherTheoryTutorialShowService showService;
	

	// Constructors -----------------------------------------------------------

	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("show", this.showService);
		}
}