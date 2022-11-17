package acme.features.teacher.followUp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.FollowUp;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Teacher;

@Service
public class TeacherFollowUpShowService implements AbstractShowService<Teacher, FollowUp> {
	
	@Autowired
	protected TeacherFollowUpRepository repository;
	
	@Override
	public boolean authorise(final Request<FollowUp> request) {
		assert request != null;
		
		boolean result;
		int followUpId;
		FollowUp followUp;
		
		followUpId = request.getModel().getInteger("id");
		followUp = this.repository.findOneFollowUpById(followUpId);
		result = followUp != null;
		
		return result;
	}
	@Override
	public FollowUp findOne(final Request<FollowUp> request) {
		assert request != null;
		
		FollowUp result;
		int followUpId;
		
		followUpId = request.getModel().getInteger("id");
		result = this.repository.findOneFollowUpById(followUpId);
		
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