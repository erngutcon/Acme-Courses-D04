package acme.features.learner.helpRequest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.HelpRequest;
import acme.entities.StatusHelpRequest;
import acme.features.administrator.configuration.AdministratorConfigurationRepository;
import acme.features.teacher.helpRequest.TeacherHelpRequestRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Learner;
import acme.roles.Teacher;

@Service
public class LearnerHelpRequestCreateService implements AbstractCreateService<Learner, HelpRequest>{

	@Autowired
	protected LearnerHelpRequestRepository repository;
	
	@Autowired
	protected AdministratorConfigurationRepository configuration;
	
	@Autowired
	protected TeacherHelpRequestRepository teacherRepository;
	
	@Override	public boolean authorise(final Request<HelpRequest> request) {
		assert request != null;
		
		boolean result;
		
		result = request.getPrincipal().hasRole(Learner.class);
		
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
		final List<Teacher> teachers = this.teacherRepository.findAllTeachers();
		model.setAttribute("teachers", teachers);
	}

	@Override
	public HelpRequest instantiate(final Request<HelpRequest> request) {
		assert request != null;
		
		Date creationMoment;
		HelpRequest result;
		result = new HelpRequest();

		

		result.setLearner(this.repository.findLearnerById(request.getPrincipal().getAccountId()));
		
		result.setStatus(StatusHelpRequest.PROPOSED);
		
		
		// Manage unique code
		String ticker = "";
		int contador = 1;

		do {
			ticker = this.createTicker(contador);
			contador++;
		}while (!this.isTickerUnique(ticker,contador));
		result.setTicker(ticker);
		result.setPublish(false);
		
		creationMoment = new Date(System.currentTimeMillis() - 1000);
		result.setCreationMoment(creationMoment);
		
		return result;
	}

	@Override
	public void validate(final Request<HelpRequest> request, final HelpRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		if (!errors.hasErrors("initDate")) {
			Calendar calendar;

			calendar = new GregorianCalendar();
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			errors.state(request, entity.getInitDate().after(calendar.getTime()), "initDate", "learner.help-request.form.error.too-close-init-date");
		}
		if (!errors.hasErrors("finishDate")) {
			Calendar calendar;
			Date finish;
			calendar = new GregorianCalendar();
			calendar.setTime(entity.getInitDate());
			calendar.add(Calendar.MONTH, 1);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			finish = calendar.getTime();
			errors.state(request, entity.getFinishDate().after(finish), "finishDate", "learner.help-request.form.error.one-month");
		}
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
	public void create(final Request<HelpRequest> request, final HelpRequest entity) {
		assert request != null;
		assert entity !=null;
		
		final String nameTeacher = (String) request.getModel().getAttribute("teachers");
		
		final Teacher teacher = this.teacherRepository.findByName(nameTeacher);
		
		entity.setTeacher(teacher);
		
		
		this.repository.save(entity);
		
		
	}
	
	// Other business methods -------------------------

	public String numbersSecuency(final int numero) {

		String secuency = new String();
		int total;
		final ArrayList<HelpRequest> followUps = new ArrayList<>(
			this.repository.findAllHelpRequests());
		final int size = followUps.size();

		total = size + 1;

		if (String.valueOf(total).length() == 1) {
			secuency = "00" + String.valueOf(total);
		} else if (String.valueOf(total).length() == 2) {
			secuency = "0" + String.valueOf(total);
		} else if (String.valueOf(total).length() == 3) {
			secuency = "0" + String.valueOf(total);
		} else if (String.valueOf(total).length() > 3) {
			secuency = String.valueOf(total);
		}
		
		return secuency;

	}

	public String createTicker(final int contador) {

		// The ticker must be as follow:XXXX
		String ticker = new String();
		final String TICKER_PREFIX = "HRQ";

		// Set ticker format
		ticker = TICKER_PREFIX + "-" + this.numbersSecuency(contador);

		return ticker;

	}
	public boolean isTickerUnique(final String ticker, final int contador) {

		Boolean result = true;

		final ArrayList<HelpRequest> helpRequests = new ArrayList<>(
				this.repository.findAllHelpRequests());

		final ArrayList<String> tickers = new ArrayList<>();

		for (final HelpRequest f : helpRequests) {
			tickers.add(f.getTicker());
		}

		if (tickers.contains(ticker)) {
			result = false;
			this.createTicker(contador);
		}

		return result;
	}

}
