package acme.features.administrator.post;

/*
 * AnonymousAnnouncementCreateService.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Post;
import acme.features.authenticated.post.AuthenticatedPostRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorPostCreateService implements AbstractCreateService<Administrator, Post> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AuthenticatedPostRepository repository;
	
	// AbstractCreateService<Administrator, Announcement> interface --------------


	@Override
	public boolean authorise(final Request<Post> request) {
		assert request != null;

		return true;
	}

	@Override
	public Post instantiate(final Request<Post> request) {
		assert request != null;

		Post result;
		
		Date instantiationMoment;

		instantiationMoment = new Date(System.currentTimeMillis() - 1); 

		result = new Post();

		result.setInstantiationMoment(instantiationMoment);
		return result;
	}

	@Override
	public void bind(final Request<Post> request, final Post entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		
		request.bind(entity, errors, "instantiationMoment", "caption", "message", "flag", "hyperlink");
		
	}

	@Override
	public void validate(final Request<Post> request, final Post entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void unbind(final Request<Post> request, final Post entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "instantiationMoment", "caption", "message", "flag", "hyperlink");
		model.setAttribute("confirmation", false);
	}

	@Override
	public void create(final Request<Post> request, final Post entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}