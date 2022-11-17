package acme.features.teacher.labTutorial;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.entities.Configuration;
import acme.entities.Course;
import acme.entities.LabTutorial;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Teacher;

@Repository
public interface TeacherLabTutorialRepository extends AbstractRepository {

	@Query("SELECT userAccount FROM UserAccount userAccount WHERE userAccount.id = :id")
	UserAccount findOneUserAccountById(@Param("id") int id);

	@Query("SELECT c FROM Course c WHERE c.teacher.id = :id")
	Collection<Course> findAllCoursesByTeacher(@Param("id") int id);
	
	@Query("SELECT t FROM Teacher t WHERE t.id = :id")
	Teacher findOneTeacherById(@Param("id") int id);
	
	@Query("SELECT t FROM Teacher t WHERE t.userAccount.id = :id")
	Teacher findOneTeacherByAccountId(@Param("id") int id);
	
	@Query("SELECT c FROM Course c WHERE c.id = :id")
	Course findOneCourseById(@Param("id") int id);
	
	@Query("select lt from Course c, Register r, LabTutorial lt where c.id = r.course.id and r.labTutorial.id = lt.id and c.id = :id")
	Collection<LabTutorial> findManyLabTutorialsByCourseId(@Param("id") int id);
	
	@Query("SELECT lt FROM Teacher t, Course c, Register r, LabTutorial lt WHERE t.id = c.teacher.id AND c.id = r.course.id AND r.labTutorial.id = lt.id AND t.id = :id")
	Collection<LabTutorial> findManyLabTutorialsByTeacherId(@Param("id") int id);
	
	@Query("SELECT item FROM Course item")
	Collection<Course> findAllCourses();
	
	@Query("select c from Configuration c")
	Configuration findConfiguration();
	
	@Query("select t.cost.amount, t.cost.currency from Register r, Course c, TheoryTutorial t where c.id = r.course.id and r.theoryTutorial.id = t.id and c.id = :id")
	List<Object[]> getCourseTheoryTutorialsPrice(@Param("id") int id);

	@Query("select l.cost.amount, l.cost.currency from Register r, Course c, LabTutorial l where c.id = r.course.id and r.labTutorial.id = l.id and c.id = :id")
	List<Object[]> getCourseLabTutorialsPrice(@Param("id") int id);
	
	@Query("select distinct te.id from Teacher te, Course c, Register r, LabTutorial l where te.id = c.teacher.id and c.id = r.course.id and r.labTutorial.id = :id")
	Integer findTeacherByLabTutorialId(@Param("id") int id);
	
//	@Query("select t from TheoryTutorial t where t.id = :id")
//	TheoryTutorial findOneTheoryTutorialById(@Param("id") int id);
	
	@Query("select l from LabTutorial l where l.id = :id")
	LabTutorial findOneLabTutorialById(@Param("id") int id);
}