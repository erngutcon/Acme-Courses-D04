package acme.features.teacher.labTutorial;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.LabTutorial;
import acme.framework.controllers.AbstractController;
import acme.roles.Teacher;

@Controller
public class TeacherLabTutorialController extends AbstractController<Teacher, LabTutorial> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected TeacherLabTutorialListService listService;

	@Autowired
	protected TeacherLabTutorialShowService showService;
	

	// Constructors -----------------------------------------------------------

	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("show", this.showService);
		}
}