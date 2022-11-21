package acme.testing.any.theoryTutorial;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyTheoryTutorialListAllTest extends TestHarness {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/any/theory-tutorial/list-all.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String ticker, final String title, final String abstractText, final String cost, final String hyperlink) {
		super.signIn("administrator", "administrator");

		super.clickOnMenu("Any", "Theory tutorials");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, abstractText);

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("ticker", ticker);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("abstractText", abstractText);
		super.checkInputBoxHasValue("cost", cost);
		super.checkInputBoxHasValue("hyperlink", hyperlink);
		
		super.signOut();
	}

	@Test
	@Order(20)
	public void negativeTest() {
		// There's no negative test case for this listing, since it doesn't
		// involve filling in any forms.
	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		// There is no hacking test case for this listing, since anyone
		// have access to this listing
	}

}

