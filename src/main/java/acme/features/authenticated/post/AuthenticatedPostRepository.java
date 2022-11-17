/*
 * AnyDutyRepository.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.authenticated.post;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.entities.Configuration;
import acme.entities.Post;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedPostRepository extends AbstractRepository {

	@Query("select a from Post a where a.id = :id")
	Post findOnePostById(@Param("id") int id);
	
	@Query("select a from Post a where TO_DAYS(current_date()) - TO_DAYS(a.instantiationMoment) < 30")
	Collection<Post> findAllPosts();
	
	@Query("select c from Configuration c")
	Configuration findConfiguration();
	
}