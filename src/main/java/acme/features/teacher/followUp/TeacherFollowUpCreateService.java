/*
 * TeacherFollowUpCreateService.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.teacher.followUp;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.FollowUp;
import acme.entities.HelpRequest;
import acme.features.teacher.helpRequest.TeacherHelpRequestRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Teacher;

@Service
public class TeacherFollowUpCreateService implements AbstractCreateService<Teacher, FollowUp> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected TeacherFollowUpRepository repository;
	
	@Autowired
	protected TeacherHelpRequestRepository helpRequestRepository;
	

	// AbstractCreateService<Teacher, FollowUp> interface -------------------------

	@Override
	public boolean authorise(final Request<FollowUp> request) {
		assert request != null;

		boolean result;

		result = request.getPrincipal().hasRole(Teacher.class);

		return result;
	}

	@Override
	public FollowUp instantiate(final Request<FollowUp> request) {
		assert request != null;

		FollowUp result;
		Date moment;
		final HelpRequest helpRequest;
		final int hpId;

		hpId = request.getModel().getInteger("helpRequestId");
		helpRequest = this.helpRequestRepository.findOneHelpRequestById(hpId);
		
		moment = new Date(System.currentTimeMillis() - 1);

		result = new FollowUp();
		result.setInstantiationMoment(moment);

		// Manage unique code
		String ticker = "";

		do
			ticker = this.createTicker(helpRequest);
		while (!this.isTickerUnique(ticker, helpRequest));
		result.setSequenceNumber(ticker);
		result.setHelpRequest(helpRequest);

		return result;
	}

	@Override
	public void bind(final Request<FollowUp> request, final FollowUp entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "sequenceNumber", "message", "hyperlink");
	}

	@Override
	public void validate(final Request<FollowUp> request, final FollowUp entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		boolean confirmation;

		confirmation = request.getModel().getBoolean("confirmation");
		errors.state(request, confirmation, "confirmation", "teacher.follow-up.confirmation.error");

	}

	@Override
	public void unbind(final Request<FollowUp> request, final FollowUp entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "sequenceNumber", "instantiationMoment", "message", "hyperlink");
		model.setAttribute("helpRequestId", entity.getHelpRequest().getId());
		model.setAttribute("confirmation", false);
	}

	@Override
	public void create(final Request<FollowUp> request, final FollowUp entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
	}

	// Other business methods -------------------------

		public String numbersSecuency(final HelpRequest helpRequest) {

			String secuency = "";
			int total;
			final ArrayList<FollowUp> followUps = new ArrayList<>(
				this.repository.findFollowUpsByHelpRequest(helpRequest.getId()));
			final int size = followUps.size();
	
			total = size + 1;
	
			if (String.valueOf(total).length() == 1) {
				secuency = "000" + String.valueOf(total);
			} else if (String.valueOf(total).length() == 2) {
				secuency = "00" + String.valueOf(total);
			} else if (String.valueOf(total).length() == 3) {
				secuency = "0" + String.valueOf(total);
			} else if (String.valueOf(total).length() > 3) {
				secuency = String.valueOf(total);
			}
			
			return secuency;

		}

		public String createTicker(final HelpRequest helpRequest) {

			// The ticker must be as follow:XXXX
			String ticker = "";
			final String TICKER_PREFIX = helpRequest.getTicker();

			// Set ticker format
			ticker = TICKER_PREFIX + ":" + this.numbersSecuency(helpRequest);

			return ticker;

		}
		public boolean isTickerUnique(final String ticker, final HelpRequest helpRequest) {

			Boolean result = true;

			final ArrayList<FollowUp> followUps = new ArrayList<>(
					this.repository.findFollowUpsByHelpRequest(helpRequest.getId()));

			final ArrayList<String> tickers = new ArrayList<>();

			for (final FollowUp f : followUps) {
				tickers.add(f.getSequenceNumber());
			}

			if (tickers.contains(ticker)) {
				result = false;
				this.createTicker(helpRequest);
			}

			return result;

		}
}
