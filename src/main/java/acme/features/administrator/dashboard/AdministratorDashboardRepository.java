package acme.features.administrator.dashboard;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {


	// Manage TheoryTutorial
	@Query("select count(i) from TheoryTutorial i")
	Integer totalNumberOfTheoryTutorials();

	@Query("select avg(i.cost.amount), i.cost.currency from TheoryTutorial i group by i.cost.currency")
	List<Object> averageCostOfTheoryTutorials();

	@Query("select stddev(i.cost.amount), i.cost.currency from TheoryTutorial i group by i.cost.currency")
	List<Object> deviationCostOfTheoryTutorials();
	
	@Query("select min(i.cost.amount), i.cost.currency from TheoryTutorial i group by i.cost.currency")
	List<Object> minimumCostOfTheoryTutorials();

	@Query("select max(i.cost.amount), i.cost.currency from TheoryTutorial i group by i.cost.currency")
	List<Object> maximumCostOfTheoryTutorials();
	
	// Manage LabTutorial
	@Query("select count(i) from LabTutorial i")
	Integer totalNumberOfLabTutorials();
	
	@Query("select avg(i.cost.amount), i.cost.currency from LabTutorial i group by i.cost.currency")
	List<Object> averageCostOfLabTutorials();

	@Query("select stddev(i.cost.amount), i.cost.currency from LabTutorial i group by i.cost.currency")
	List<Object> deviationCostOfLabTutorials();

	@Query("select min(i.cost.amount), i.cost.currency from LabTutorial i group by i.cost.currency")
	List<Object> minimumCostOfLabTutorials();
	
	@Query("select max(i.cost.amount), i.cost.currency from LabTutorial i group by i.cost.currency")
	List<Object> maximumCostOfLabTutorials();
	
	// Manage TOTALS
	@Query("select count(p) from HelpRequest p where p.status = 'PROPOSED'")
	Integer totalNumberOfProposedHelpRequests();

	@Query("select count(p) from HelpRequest p where p.status = 'ACCEPTED'")
	Integer totalNumberOfAcceptedHelpRequests();

	@Query("select count(p) from HelpRequest p where p.status = 'DENIED'")
	Integer totalNumberOfDeniedHelpRequests();

	// Manage PROPOSED
	@Query("select avg(p.budget.amount), p.budget.currency from HelpRequest p where p.status = 'PROPOSED' group by p.budget.currency")
	List<Object> averageBudgetOfProposedHelpRequests();

	@Query("select stddev(p.budget.amount), p.budget.currency from HelpRequest p where p.status = 'PROPOSED' group by p.budget.currency")
	List<Object> deviationBudgetOfProposedHelpRequests();

	@Query("select min(p.budget.amount), p.budget.currency from HelpRequest p where p.status = 'PROPOSED' group by p.budget.currency")
	List<Object> minimumBudgetOfProposedHelpRequests();

	@Query("select max(p.budget.amount), p.budget.currency from HelpRequest p where p.status = 'PROPOSED' group by p.budget.currency")
	List<Object> maximumBudgetOfProposedHelpRequests();

	// Manage ACCEPTED
	@Query("select avg(p.budget.amount), p.budget.currency from HelpRequest p where p.status = 'ACCEPTED' group by p.budget.currency")
	List<Object> averageBudgetOfAcceptedHelpRequests();

	@Query("select stddev(p.budget.amount), p.budget.currency from HelpRequest p where p.status = 'ACCEPTED' group by p.budget.currency")
	List<Object> deviationBudgetOfAcceptedHelpRequests();

	@Query("select min(p.budget.amount), p.budget.currency from HelpRequest p where p.status = 'ACCEPTED' group by p.budget.currency")
	List<Object> minimumBudgetOfAcceptedHelpRequests();

	@Query("select max(p.budget.amount), p.budget.currency from HelpRequest p where p.status = 'ACCEPTED' group by p.budget.currency")
	List<Object> maximumBudgetOfAcceptedHelpRequests();

	// Manage DENIED
	@Query("select avg(p.budget.amount), p.budget.currency from HelpRequest p where p.status = 'DENIED' group by p.budget.currency")
	List<Object> averageBudgetOfDeniedHelpRequests();

	@Query("select stddev(p.budget.amount), p.budget.currency from HelpRequest p where p.status = 'DENIED' group by p.budget.currency")
	List<Object> deviationBudgetOfDeniedHelpRequests();

	@Query("select min(p.budget.amount), p.budget.currency from HelpRequest p where p.status = 'DENIED' group by p.budget.currency")
	List<Object> minimumBudgetOfDeniedHelpRequests();

	@Query("select max(p.budget.amount), p.budget.currency from HelpRequest p where p.status = 'DENIED' group by p.budget.currency")
	List<Object> maximumBudgetOfDeniedHelpRequests();
	
}