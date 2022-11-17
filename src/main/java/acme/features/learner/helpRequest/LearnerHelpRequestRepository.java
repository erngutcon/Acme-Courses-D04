/*
 * LearnerPatronageRepository.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.learner.helpRequest;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.entities.HelpRequest;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Learner;

@Repository
public interface LearnerHelpRequestRepository extends AbstractRepository {
	
	@Query("select learner from Learner learner where learner.id =:id")
	Learner findLearnerById(@Param("id") int id);
	
	@Query("select learner from Learner learner")
	List<Learner> findAllLearners();

	@Query("select ua from UserAccount ua where ua.id = :id")
	UserAccount findOneUserAccountById(@Param("id") int id);

	@Query("select hp from HelpRequest hp where hp.id = :id")
	HelpRequest findOneHelpRequestById(@Param("id") int id);

	@Query("select hp.learner.id from HelpRequest hp where hp.id = :id")
	Integer findLearnerByHelpRequestId(@Param("id") int id);
	
	@Query("select hp from HelpRequest hp where hp.learner.id = :id")
	Collection<HelpRequest> findManyHelpRequestsByLearner(@Param("id") int id);
	
}
