package acme.features.learner.helpRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.HelpRequest;
import acme.features.administrator.configuration.AdministratorConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Learner;

@Service
public class LearnerHelpRequestPublishService implements AbstractUpdateService<Learner, HelpRequest>{

	@Autowired
	protected LearnerHelpRequestRepository repository;
	
	@Autowired
	protected AdministratorConfigurationRepository configuration;
	
	@Override
	public boolean authorise(final Request<HelpRequest> request) {
		assert request != null;
		
		boolean result;
		int helpRequestId;
		HelpRequest helpRequest;
		
		helpRequestId = request.getModel().getInteger("id");
		helpRequest = this.repository.findOneHelpRequestById(helpRequestId);
		result = helpRequest!=null;
		
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

		request.unbind(entity, model, "ticker", "statement", "creationMoment", "budget", "initDate", "finishDate", "status", "hyperlink", "publish");
		
		
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
		
		if(!errors.hasErrors("budget")) {
			final String upperCaseCurrency = entity.getBudget().getCurrency().toUpperCase();
			boolean accepted = false;
			
			// Manage likely currencies
			for (final String acceptedCurrency : this.configuration.findConfiguration().getAcceptedCurrencies().toUpperCase().split(",")) {
				if (upperCaseCurrency.equals(acceptedCurrency)) {
					accepted = true;
					break;
				}
			}
			errors.state(request, entity.getBudget().getAmount() > 0, "budget", "learner.help-request.form.error.negative-budget");
			errors.state(request, accepted, "budget", "learner.help-request.form.error.non-accepted-currency");
		}
	}

	@Override
	public void update(final Request<HelpRequest> request, final HelpRequest entity) {
		assert request != null;
		assert entity != null;
		entity.setPublish(true);
		this.repository.save(entity);
		
	}

}
