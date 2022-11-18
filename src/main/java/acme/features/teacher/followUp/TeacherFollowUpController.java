package acme.features.teacher.followUp;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.FollowUp;
import acme.framework.controllers.AbstractController;
import acme.roles.Teacher;

@Controller
public class TeacherFollowUpController extends AbstractController<Teacher, FollowUp> {

	@Autowired
	protected TeacherFollowUpListService listService;

	@Autowired
	protected TeacherFollowUpShowService showService;
	
	@Autowired
	protected TeacherFollowUpCreateService createService;
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("show", this.showService);
		super.addCommand("create", this.createService);
	}
}