/*
 * AnonymousBlinkCreateService.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.any.blink;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Blink;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractCreateService;

@Service
public class BlinkCreateService implements AbstractCreateService<Any, Blink> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected BlinkRepository repository;
	
	/*@Autowired
	protected SpamChecker checker;*/

	// AbstractCreateService<Administrator, Blink> interface --------------


	@Override
	public boolean authorise(final Request<Blink> request) {
		assert request != null;

		return true;
	}

	@Override
	public Blink instantiate(final Request<Blink> request) {
		assert request != null;

		Blink result;

		result = new Blink();

		return result;
	}

	@Override
	public void bind(final Request<Blink> request, final Blink entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Date instantiationMoment;

		instantiationMoment = new Date(System.currentTimeMillis() - 1);
		request.bind(entity, errors, "caption", "authorAlias", "message", "email");
		entity.setInstantiationMoment(instantiationMoment);
	}

	@Override
	public void validate(final Request<Blink> request, final Blink entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean confirmation;

		confirmation = request.getModel().getBoolean("confirmation");
		errors.state(request, confirmation, "confirmation", "any.blink.form.label.confirmation");
	}

	@Override
	public void unbind(final Request<Blink> request, final Blink entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "caption", "authorAlias", "message", "email");
		model.setAttribute("confirmation", false);
		model.setAttribute("readonly", false);
		model.setAttribute("showmoment", false);
	}

	@Override
	public void create(final Request<Blink> request, final Blink entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
