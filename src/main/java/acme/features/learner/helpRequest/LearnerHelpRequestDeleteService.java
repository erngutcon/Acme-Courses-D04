package acme.features.learner.helpRequest;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.FollowUp;
import acme.entities.HelpRequest;
import acme.features.learner.followUp.LearnerFollowUpRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Learner;

@Service
public class LearnerHelpRequestDeleteService implements AbstractDeleteService<Learner, HelpRequest> {

	@Autowired
	protected LearnerHelpRequestRepository repository;
	
	@Autowired
	protected LearnerFollowUpRepository followUpRepository;
	
	@Override
	public boolean authorise(final Request<HelpRequest> request) {
		assert request != null;
		
		boolean result;
		int helpRequestId;
		HelpRequest helpRequest;
		
		helpRequestId = request.getModel().getInteger("id");
		helpRequest = this.repository.findOneHelpRequestById(helpRequestId);
		result = helpRequest != null;
		
		return result;
	}

	@Override
	public void bind(final Request<HelpRequest> request, final HelpRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "ticker", "statement", "creationMoment", "budget", "initDate", "finishDate", "status", "hyperlink");
		
	}

	@Override
	public void unbind(final Request<HelpRequest> request, final HelpRequest entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "statement", "creationMoment", "budget", "initDate", "finishDate", "status", "hyperlink");
		
	}

	@Override
	public HelpRequest findOne(final Request<HelpRequest> request) {
		assert request != null;

		int helpRequestId;
		HelpRequest result;

		helpRequestId = request.getModel().getInteger("id");
		result = this.repository.findOneHelpRequestById(helpRequestId);

		return result;
	
	}

	@Override
	public void validate(final Request<HelpRequest> request, final HelpRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void delete(final Request<HelpRequest> request, final HelpRequest entity) {
		assert request != null;
		assert entity != null;
		
		final Collection<FollowUp> pr;
		
		pr = this.repository.findManyFollowUpsByHelpRequestId(entity.getId());
		for(final FollowUp p : pr) {
			this.repository.delete(p);
		}
		
		this.repository.delete(entity);
		
	}

}
