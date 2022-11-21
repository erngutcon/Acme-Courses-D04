package acme.testing.any.blink;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyBlinkListAllTest extends TestHarness {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	//	WARNING!!: The listed blinks are instantiated on 20 and 21 of November, and they are shown until
	//	20 and 21 of December, if they are ran after that, this test will fail because, according to the
	// requirements, "List the blinks that are not older than one month"
	
	@ParameterizedTest
	@CsvFileSource(resources = "/any/blink/list-all.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String caption, final String authorAlias, final String instantiationMoment, final String message, final String email) {
		super.signIn("administrator", "administrator");

		super.clickOnMenu("Any", "Blinks");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, caption);
		super.checkColumnHasValue(recordIndex, 1, instantiationMoment);
		super.checkColumnHasValue(recordIndex, 2, authorAlias);

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("caption", caption);
		super.checkInputBoxHasValue("authorAlias", authorAlias);
		super.checkInputBoxHasValue("message", message);
		super.checkInputBoxHasValue("email", email);
		super.checkInputBoxHasValue("instantiationMoment", instantiationMoment);
		
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

