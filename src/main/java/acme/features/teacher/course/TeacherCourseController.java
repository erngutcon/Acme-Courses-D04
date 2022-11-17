package acme.features.teacher.course;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Course;
import acme.framework.controllers.AbstractController;
import acme.roles.Teacher;

@Controller
public class TeacherCourseController extends AbstractController<Teacher, Course> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected TeacherCourseListService listService;

	@Autowired
	protected TeacherCourseShowService showService;
	

	// Constructors -----------------------------------------------------------

	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("show", this.showService);
		}
}