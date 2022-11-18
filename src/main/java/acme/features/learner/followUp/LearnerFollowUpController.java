package acme.features.learner.followUp;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.FollowUp;
import acme.framework.controllers.AbstractController;
import acme.roles.Learner;

@Controller
public class LearnerFollowUpController extends AbstractController<Learner, FollowUp> {

	@Autowired
	protected LearnerFollowUpListService listService;

	@Autowired
	protected LearnerFollowUpShowService showService;
	
	@Autowired
	protected LearnerFollowUpCreateService createService;
	

	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("show", this.showService);
		super.addCommand("create", this.createService);
	}
}