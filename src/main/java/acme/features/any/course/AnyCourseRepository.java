package acme.features.any.course;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.entities.Course;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyCourseRepository extends AbstractRepository{

	
	@Query("select t from Course t ")
	Collection<Course> findCourses();

	@Query("select t from Course t where t.id = :id")
	Course findOneCourseById(@Param("id") int id);

	@Query("select  i.cost.amount, i.cost.currency from Course c, Register r, TheoryTutorial i where c.id = r.course.id and r.theoryTutorial.id = i.id and c.id = :id")
	List<Object[]> getCourseTheoryTutorialPrice(@Param("id") int id);
	
	@Query("select i.cost.amount, i.cost.currency from Course c, Register r, LabTutorial i where c.id = r.course.id and r.labTutorial.id = i.id and c.id = :id")
	List<Object[]> getCourseLabTutorialPrice(@Param("id") int id);



}
