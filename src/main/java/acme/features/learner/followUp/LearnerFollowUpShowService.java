package acme.features.learner.followUp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.FollowUp;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Learner;

@Service
public class LearnerFollowUpShowService implements AbstractShowService<Learner, FollowUp> {
	
	@Autowired
	protected LearnerFollowUpRepository repository;
	
	@Override
	public boolean authorise(final Request<FollowUp> request) {
		assert request != null;
		
		boolean result;
		int id;
		FollowUp followUp;
		
		id = request.getModel().getInteger("id");
		followUp = this.repository.findOneFollowUpById(id);
		result = followUp != null;
		
		return result;
	}
	@Override
	public FollowUp findOne(final Request<FollowUp> request) {
		assert request != null;
		
		FollowUp result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findOneFollowUpById(id);
		
		return result;
	}
	
	@Override
	public void unbind(final Request<FollowUp> request, final FollowUp entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "sequenceNumber", "instantiationMoment", "message", "hyperlink");
	}
}