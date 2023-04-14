package testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rec.global.utils.*;

public class RecruitmentTestCases extends CommonPageObject{

	/**
	 * This is used to instantiate the browser and launch the application
	 * @author ankit
	 * @throws Throwable 
	 * @since 12/04/2023
	 */
	@BeforeClass
	public void launchApplication() throws Throwable{
		Assert.assertTrue(launchWebsite(),"Error in Opening Website");
	}

	/**
	 * This test case is to test the positive scenario of login to the application
	 * @author ankit
	 * @since 12/04/2023
	 */
	@Test(priority=1,testName="TC_01_Login", dataProvider="Login Details")
	public void tc_01_loginDbox(String username, String password) {
		Assert.assertTrue(login(username, password),"Error while Login");
		 
	}

	/**
	 * This test case is to test the positive scenario of composing the mail
	 * @author ankit
	 * @since 12/04/2023
	 */
	@Test(priority=2,testName="TC_02_NavigateToJobOpeningPage")
	public void tc_02_navigatetoLink() {
		Assert.assertTrue(clickOnLink(), "Error while composing the mail");
	}
	
	/**
	 * This method is to close browser the method post class is over 
	 * @author ankit
	 * @since 12/04/2023
	 */
	@AfterClass
	public void closeApplication() {
		driver.close();
		report.endTest(test);
		report.flush();
	}

	
	/**
	 * This is a Data Provider returning values for Username and Password
	 * @author ankit
	 * @return Double Dimensional Array of Object Type
	 * @since 12/04/2023
	 */
	@DataProvider(name="Login Details")
	public Object[][] loginDetails() {
		Object[][] parameters= {{config("username"),config("password")}};
		return parameters;
	}
	

}

