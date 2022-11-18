package acme.features.teacher.followUp;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.entities.FollowUp;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Learner;

@Repository
public interface TeacherFollowUpRepository extends AbstractRepository {

	@Query("select followUp from FollowUp followUp where followUp.id = :id")
	FollowUp findOneFollowUpById(@Param("id")  int id);
	
	@Query("select ua from UserAccount ua where ua.id = :id")
	UserAccount findOneUserAccountById(@Param("id")  int id);
	
	@Query("select l from Learner l where l.userAccount.id = :id")
	Learner findOneLearnerById(@Param("id")  int id);
	
	@Query("select f from FollowUp f, HelpRequest h where f.helpRequest.id = h.id and h.teacher.id = :id")
	Collection<FollowUp> findManyFollowUpsByTeacher(@Param("id")  int id);
	
	@Query("select followUp from FollowUp followUp")
	List<FollowUp> findAllFollowUps();
	
	@Query("select t.id from FollowUp f, HelpRequest h, Teacher t where f.helpRequest.id = h.id and h.teacher.id = t.id and f.id = :id")
	Integer findTeacherByFollowUpId(@Param("id")  int id);

	@Query("select followUp from FollowUp followUp where followUp.helpRequest.id = :id")
	Collection<FollowUp> findFollowUpsByHelpRequest(@Param("id") int id);
}
