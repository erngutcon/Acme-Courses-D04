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

package acme.features.any.labTutorial;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.entities.LabTutorial;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyLabTutorialRepository extends AbstractRepository {

	@Query("select lt from LabTutorial lt where lt.id = :id")
	LabTutorial findOneLabTutorialById(@Param("id") int id);
	
	@Query("select lt from LabTutorial lt")
	List<LabTutorial> findAllLabTutorials();

	@Query("select lt from Course c, Register r, LabTutorial lt where c.id = r.course.id and r.labTutorial.id = lt.id and c.id = :id")
	Collection<LabTutorial> findManyLabTutorialsByCourseId(@Param("id") int id);
	
	
}
