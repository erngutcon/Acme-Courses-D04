/*
 * EmployerDutyCreateTest.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.testing.any.blink;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyBlinkCreateTest extends TestHarness {

	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/any/blink/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String caption, final String authorAlias, final String instantiationMoment, final String message, final String email) {
		super.signIn("administrator", "administrator");

		super.clickOnMenu("Any", "Blinks");
		super.checkListingExists();

		super.clickOnButton("Create blink");
		super.fillInputBoxIn("caption", caption);
		super.fillInputBoxIn("authorAlias", authorAlias);
		super.fillInputBoxIn("message", message);
		super.fillInputBoxIn("email", email);
		super.fillInputBoxIn("confirmation", "true");
		super.clickOnSubmit("Create blink");
		
		super.checkColumnHasValue(recordIndex, 0, caption);
		super.checkColumnHasValue(recordIndex, 2, authorAlias);

		super.clickOnListingRecord(recordIndex);
		super.checkInputBoxHasValue("caption", caption);
		super.checkInputBoxHasValue("authorAlias", authorAlias);
		super.checkInputBoxHasValue("message", message);
		super.checkInputBoxHasValue("email", email);

		super.signOut();
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/any/blink/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int recordIndex, final String caption, final String authorAlias, final String instantiationMoment, final String message, final String email, final Boolean confirmation) {
		super.signIn("administrator", "administrator");

		super.clickOnMenu("Any", "Blinks");
		super.checkListingExists();
		super.sortListing(0, "asc");

		super.clickOnButton("Create blink");
		super.fillInputBoxIn("caption", caption);
		super.fillInputBoxIn("authorAlias", authorAlias);
		super.fillInputBoxIn("message", message);
		super.fillInputBoxIn("email", email);
		super.fillInputBoxIn("confirmation", String.valueOf(confirmation));
		super.clickOnSubmit("Create blink");

		super.checkErrorsExist();

		super.signOut();
	}

}
