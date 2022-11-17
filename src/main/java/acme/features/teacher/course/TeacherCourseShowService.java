package acme.features.teacher.course;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Course;
import acme.entities.LabTutorial;
import acme.entities.TheoryTutorial;
import acme.features.administrator.configuration.AdministratorConfigurationRepository;
import acme.features.any.labTutorial.AnyLabTutorialRepository;
import acme.features.any.theoryTutorial.AnyTheoryTutorialRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractShowService;
import acme.roles.Teacher;

@Service
public class TeacherCourseShowService implements AbstractShowService<Teacher, Course> {

	@Autowired
	protected TeacherCourseRepository teacherCourseRepository;
	
	@Autowired
	protected AnyLabTutorialRepository labTutorialRepository;
	
	@Autowired
	protected AnyTheoryTutorialRepository theoryTutorialRepository;
	
	@Autowired
	protected AdministratorConfigurationRepository configurationRepository;

	@Override
	public boolean authorise(final Request<Course> request) {
		assert request != null;

		boolean result;
		int id;
		Course course;
		
		id = request.getModel().getInteger("id");
		course = this.teacherCourseRepository.findOneCourseById(id);
		result = course != null && this.teacherCourseRepository.findTeacherByCourseId(id) == request.getPrincipal().getActiveRoleId();

		return result;
	}

	@Override
	public Course findOne(final Request<Course> request) {
		assert request != null;

		int id;
		Course result;
		
		id = request.getModel().getInteger("id");
		result = this.teacherCourseRepository.findOneCourseById(id);

		return result;
	}
	
	@Override
	public void unbind(final Request<Course> request, final Course entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		final int courseId = request.getModel().getInteger("id");
		//int teacherId = this.teacherCourseRepository.findTeacherByCourseId(courseId);
		final List<Object[]> priceTheoryTutorials = this.teacherCourseRepository.getCourseLabTutorialsPrice(courseId);
		final List<Object[]> priceLabTutorials= this.teacherCourseRepository.getCourseTheoryTutorialsPrice(courseId);
		final Money  moneyTheoryTutorials = this.convertToLocalCurrencyAndSum(priceTheoryTutorials);
		final Money moneyLabTutorials = this.convertToLocalCurrencyAndSum(priceLabTutorials);
		
		final Money totalPrice = new Money();
		totalPrice.setCurrency(moneyTheoryTutorials.getCurrency());
		totalPrice.setAmount(moneyTheoryTutorials.getAmount()+moneyLabTutorials.getAmount());
		model.setAttribute("cost", totalPrice);
		
		

		boolean existsTheoryTutorial = false;
		boolean existsLabTutorial = false;
		
		final Collection<TheoryTutorial> theoryTutorials  = this.theoryTutorialRepository.findManyTheoryTutorialsByCourseId(courseId);
		final Collection<LabTutorial> labTutorials  = this.labTutorialRepository.findManyLabTutorialsByCourseId(courseId);
		//final Collection<LabTutorial> labTutorials  = this.labTutorialRepository.findManyLabTutorialsByCourseIdAndTeacherId(courseId, teacherId);
		if(theoryTutorials.size()!=0) existsTheoryTutorial=true;
		if(labTutorials.size()!=0) existsLabTutorial=true;
		
		model.setAttribute("existsLabTutorial", existsLabTutorial);
		model.setAttribute("existsTheoryTutorial", existsTheoryTutorial);
		
		request.unbind(entity, model, "ticker", "caption", "abstractText", "hyperlink");
	}
	
	// Other methods
		private Money convertToLocalCurrencyAndSum(final List<Object[]> prices) {
			final Money res = new Money();
			
			final String localCurrency = this.configurationRepository.findConfiguration().getCurrency();
			Double amount;
			Double sumAmount = 0.0;
			String currency;
			
			// EUR
			final Double EUR_USD_FACTOR = 1.0006;
			final Double EUR_GBP_FACTOR = 0.881655;
						
			// USD
			final Double USD_EUR_FACTOR = 0.998169;
			final Double USD_GBP_FACTOR = 0.88121;
			
			// GBP
			final Double GBP_EUR_FACTOR = 1.14938;
			final Double GBP_USD_FACTOR = 1.137041;
			
			for (final Object[] b:prices) {
				amount = (Double) b[0];
				currency = (String) b[1];
				
				// If localCurrency = EUR
				if(localCurrency.equals("EUR")) {
					sumAmount += currency.equals("USD")
						? amount * USD_EUR_FACTOR
						: currency.equals("GBP")
						? amount * GBP_EUR_FACTOR
						: amount;
				// If localCurrency = USD
				}else if(localCurrency.equals("USD")) {
					sumAmount += currency.equals("EUR")
							? amount * EUR_USD_FACTOR
							: currency.equals("GBP")
							? amount * GBP_USD_FACTOR
							: amount;
				// If localCurrency = GBP
				}else{
					sumAmount += currency.equals("EUR")
							? amount * EUR_GBP_FACTOR
							: currency.equals("USD")
							? amount * USD_GBP_FACTOR
							: amount;
				}
			}
			
			res.setAmount(sumAmount);
			res.setCurrency(localCurrency);
			
			return res;
		}
}