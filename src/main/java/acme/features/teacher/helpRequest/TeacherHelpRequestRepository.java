/*
 * TeacherPatronageRepository.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.teacher.helpRequest;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.entities.HelpRequest;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Teacher;

@Repository
public interface TeacherHelpRequestRepository extends AbstractRepository {
	
	@Query("select teacher from Teacher teacher where teacher.userAccount.id =:id")
	Teacher findTeacherByUserId(@Param("id")  int id);
	
	@Query("select teacher from Teacher teacher where teacher.id =:id")
	Teacher findTeacherById(@Param("id")  int id);
	
	@Query("select teacher from Teacher teacher")
	List<Teacher> findAllTeachers();

	@Query("select ua from UserAccount ua where ua.id = :id")
	UserAccount findOneUserAccountById(@Param("id")  int id);

	@Query("select hp from HelpRequest hp")
	Collection<HelpRequest> findAllHelpRequests();
	
	@Query("select hp from HelpRequest hp where hp.id = :id")
	HelpRequest findOneHelpRequestById(@Param("id")  int id);

	@Query("select teacher from Teacher teacher where teacher.userAccount.username =:username")
	Teacher findByName(String username);
	
	@Query("select h.teacher.id from HelpRequest h where h.id = :id")
	Integer findTeacherByHelpRequestId(@Param("id")  int id);
	
	@Query("select hp from HelpRequest hp where hp.teacher.id = :id")
	Collection<HelpRequest> findManyHelpRequestsByTeacher(@Param("id") int id);
	
}
