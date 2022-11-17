package acme.features.teacher.course;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.entities.Course;
import acme.entities.LabTutorial;
import acme.entities.TheoryTutorial;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Teacher;

@Repository
public interface TeacherCourseRepository extends AbstractRepository {

	@Query("SELECT userAccount FROM UserAccount userAccount WHERE userAccount.id = :id")
	UserAccount findOneUserAccountById(@Param("id")  int id);

	@Query("SELECT t FROM Teacher t WHERE t.id = :id")
	Teacher findOneTeacherById(@Param("id")  int id);
	
	@Query("SELECT c FROM Course c WHERE c.id = :id")
	Course findOneCourseById(@Param("id")  int id);

	@Query("SELECT distinct tt FROM Course c, Register r, TheoryTutorial tt WHERE c.id = r.course.id AND r.theoryTutorial.id = tt.id AND c.id = :id")
	Collection<TheoryTutorial> findManyTheoryTutorialsByCourseId(@Param("id")  int id);
	
	@Query("SELECT distinct lt FROM Course c, Register r, LabTutorial lt WHERE c.id = r.course.id AND r.theoryTutorial.id = lt.id AND c.id = :id")
	Collection<LabTutorial> findManyLabTutorialsByCourseId(@Param("id")  int id);
	
	@Query("SELECT c FROM Course c WHERE c.teacher.id = :id")
	Collection<Course> findManyCoursesByTeacherId(@Param("id")  int id);
	
	@Query("SELECT c FROM Course c")
	Collection<Course> findAllCourses();
	
	@Query("SELECT t.cost.amount, t.cost.currency FROM Course c, Register r, TheoryTutorial t WHERE c.id = r.course.id AND r.theoryTutorial.id = t.id AND c.id = :id")
	List<Object[]> getCourseTheoryTutorialsPrice(@Param("id")  int id);

	@Query("SELECT l.cost.amount, l.cost.currency FROM Course c, Register r, LabTutorial l WHERE c.id = r.course.id AND r.labTutorial.id = l.id AND c.id = :id")
	List<Object[]> getCourseLabTutorialsPrice(@Param("id")  int id);
	
	@Query("SELECT c.teacher.id FROM Course c WHERE c.id = :id")
	Integer findTeacherByCourseId(@Param("id")  int id);
	
}