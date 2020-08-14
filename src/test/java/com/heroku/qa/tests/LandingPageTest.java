package com.heroku.qa.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.heroku.qa.base.BaseClass;
import com.heroku.qa.constants.Constants;

public class LandingPageTest extends BaseClass {

	@Test(priority = 1)
	public void validateTitleTest() {

		Assert.assertEquals(landingPage.getTitle(), Constants.HOME_PAGE_TITLE);

	}

	@Test(priority = 2)
	public void validateHeaderTest() {
		Assert.assertTrue(landingPage.getHeader().contains(Constants.HOME_PAGE_HEADER));
	}

}
