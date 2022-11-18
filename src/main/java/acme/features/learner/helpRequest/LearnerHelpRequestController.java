/*
 * InventorPatronageController.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.learner.helpRequest;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.HelpRequest;
import acme.framework.controllers.AbstractController;
import acme.roles.Learner;

@Controller
@RequestMapping("/learner/help-request/")
public class LearnerHelpRequestController extends AbstractController<Learner, HelpRequest> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected LearnerHelpRequestListService	listService;

	@Autowired
	protected LearnerHelpRequestShowService	showService;
	
	@Autowired
	protected LearnerHelpRequestUpdateService updateService;
	
	@Autowired
	protected LearnerHelpRequestDeleteService deleteService;
	
	@Autowired
	protected LearnerHelpRequestCreateService createService;
	
	@Autowired
	protected LearnerHelpRequestPublishService publishService;
	
	// Constructors -----------------------------------------------------------
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("show", this.showService);
		super.addCommand("update", this.updateService);
		super.addCommand("create", this.createService);
		super.addCommand("delete", this.deleteService);
		super.addCommand("publish","update", this.publishService);
	}

}
