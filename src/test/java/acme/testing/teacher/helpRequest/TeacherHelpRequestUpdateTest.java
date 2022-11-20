package acme.testing.teacher.helpRequest;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class TeacherHelpRequestUpdateTest extends TestHarness {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/teacher/help-request/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String status, final String ticker,final String statement, final String budget,
		final String creationMoment, final String initDate, final String finishDate, final String hyperlink) {
		
		super.signIn("teacher1", "teacher1");
		
		super.clickOnMenu("Teacher", "My help requests");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.fillInputBoxIn("status", status);
		
		super.clickOnSubmit("Update");
		
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.checkColumnHasValue(recordIndex, 0, ticker);
		super.checkColumnHasValue(recordIndex, 1, budget);
		super.checkColumnHasValue(recordIndex, 2, status);
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("ticker", ticker);
		super.checkInputBoxHasValue("statement", statement);
		super.checkInputBoxHasValue("creationMoment", creationMoment);
		super.checkInputBoxHasValue("initDate", initDate);
		super.checkInputBoxHasValue("finishDate", finishDate);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("hyperlink", hyperlink);
		super.checkInputBoxHasValue("status", status);

		
		super.signOut();
	}
	
	

	
	
}
