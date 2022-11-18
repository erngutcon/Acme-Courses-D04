package acme.features.learner.followUp;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.entities.FollowUp;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface LearnerFollowUpRepository extends AbstractRepository {

	@Query("select followUp from FollowUp followUp where followUp.id = :id")
	FollowUp findOneFollowUpById(@Param("id") int id);
	
	@Query("select followUp from FollowUp followUp, HelpRequest h where followUp.helpRequest.id = h.id and h.learner.id = :id")
	Collection<FollowUp> findManyFollowUpsByLearner(@Param("id") int id);
	
	@Query("select followUp from FollowUp followUp")
	List<FollowUp> findAllFollowUps();
	
	@Query("select followUp from FollowUp followUp where followUp.helpRequest.id = :id")
	Collection<FollowUp> findFollowUpsByHelpRequest(@Param("id") int id);

	@Query("select p from FollowUp p order by p.id desc")
	List<FollowUp> findLastFollowUp();

	@Query("select l.id from FollowUp followUp, HelpRequest h, Learner l where followUp.helpRequest.id = h.id and h.learner.id = l.id and followUp.id = :id")
	Integer findLearnerByFollowUpId(@Param("id") int id);
}
