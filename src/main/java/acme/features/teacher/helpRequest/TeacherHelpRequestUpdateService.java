package acme.features.teacher.helpRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.HelpRequest;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Teacher;

@Service
public class TeacherHelpRequestUpdateService implements AbstractUpdateService<Teacher, HelpRequest> {
	
		@Autowired
		protected TeacherHelpRequestRepository repository;
		

		@Override
		public boolean authorise(final Request<HelpRequest> request) {
			assert request != null;
			
			boolean result;
			
			result = request.getPrincipal().hasRole(Teacher.class); 

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

			int itemId;
			HelpRequest result;

			itemId = request.getModel().getInteger("id");
			result = this.repository.findOneHelpRequestById(itemId);

			return result;
		}

		@Override
		public void validate(final Request<HelpRequest> request, final HelpRequest entity, final Errors errors) {
			assert request != null;
			assert entity != null;
			assert errors != null;
		}

		@Override
		public void update(final Request<HelpRequest> request, final HelpRequest entity) {
			assert request != null;
			assert entity != null;
			this.repository.save(entity);
		}
	}