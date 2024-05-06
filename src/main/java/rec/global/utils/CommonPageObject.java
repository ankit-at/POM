package main.java.rec.global.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
public class CommonPageObject extends AbstractPageObject {

	public WebDriver driver;
	public WebDriverWait wait;
	public Actions action;
	public ExtentTest test;
	public ExtentReports report;
	ChromeOptions options;

	public boolean status=false;
	/**
	 * @author Ankit
	 * @category Constructor
	 * @since 12/04/2023
	 * */
	public CommonPageObject() {
		System.out.println(workDir);
		report = new ExtentReports(workDir+"/ExtentReportResults.html");
		test = report.startTest("ExtentDemo");
		//File file= new File(workDir+"/src/test/resources/ExtentReportConfig.xml");
		//report.loadConfig(file);
		driver = getWebDriver(config("browser"));
		PageFactory.initElements(driver, this);
		wait=new WebDriverWait(driver, Duration.ofSeconds(60));

	}

	@FindBy( id="mectrl_headerPicture")
	protected WebElement btnAccount;

	
	@FindBy( id="i0116")
	protected WebElement txtUserName;


	@FindBy( id="i0118")
	protected WebElement txtPassword;

	@FindBy( id="idSIButton9")
	protected WebElement btnNextnSignIn;
	
	@FindBy( id ="meInitialsButton")
	protected WebElement btnProfile;
	
	@FindBy( id ="kmsiTitle")
	protected WebElement textIntermmitentPage;
	
	@FindBy (id="declineButton")
	protected WebElement btnDecline;
	/*
	 
	  Commenting the code for now might use it in future
	 @FindBy (linkText="Recruitment")
	protected WebElement linkRecruitment;

	@FindBy (xpath="//div[contains(text(),'Job Openings')]")
	protected WebElement linkJobOpenings;

	@FindBy (xpath="(//*[contains(text(),' CREATE')])[2]")
	protected WebElement btnCreateJob;

	@FindBy (id="dept_grp_company")
	protected WebElement dropDownCompany;

	@FindBy (id="dept")
	protected WebElement dropDownDepartment;

	@FindBy (id="des_department_add")
	protected WebElement dropDownDesignation;

	@FindBy (id="emp_type_add")
	protected WebElement dropDownEmpType;

	@FindBy (id="RaiseRequisition_salary_currency")
	protected WebElement dropDownSalCurrency;

	@FindBy (id="RaiseRequisition_salary_min")
	protected WebElement textSalMin;

	@FindBy (id="RaiseRequisition_salary_max")
	protected WebElement textSalMax;

	@FindBy (id="RaiseRequisition_salary_timeframe")
	protected WebElement dropDownSalTimeFrame;

	@FindBy (id="designation_title")
	protected WebElement dropDownDesgTitle;

	@FindBy (id="RaiseRequisition_key_skills")
	protected WebElement textNaukri;

	@FindBy (xpath= "//input[@type='text'][@value='Select Office Location']")
	protected WebElement searchBoxLocation;

	@FindBy (id="RaiseRequisition_designation_display_name")
	protected WebElement textDisplayName;

	@FindBy (id="addempform1_es_")
	protected WebElement errorArea;

	@FindBy (id="RaiseRequisition_is_remote")
	protected WebElement checkBoxRemote;

	@FindBy (id="RaiseRequisition_experience_from")
	protected WebElement dropDownExpFrom;

	@FindBy (id="RaiseRequisition_experience_to")
	protected WebElement dropDownExpTo;

	@FindBy (id="RaiseRequisition_experience_yrs_month")
	protected WebElement dropDownExpYrMon;

	@FindBy (xpath="/div[@role='textbox']")
	protected WebElement textAreaJD;

	@FindBy (id="saveContinue")
	protected WebElement btnSaveContinue;*/


	/**
	 * This method used for taking screenshots and attches them to report index.html
	 * @return boolean
	 * @author ankit
	 * @return
	 * @since 12/04/2023
	 */
	public String takeScreenshot() throws Exception {
		String timeStamp;
		File src = ((ChromeDriver) driver).getScreenshotAs(OutputType.FILE);
		File screenShotName;		
		//The below method will save the screen shot in d drive with name "screenshot.png"
		timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()); 
		screenShotName = new File(workDir + "/src/test/resources/results"+timeStamp+".png");
		try {
			FileHandler.copy(src, screenShotName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String filePath = screenShotName.toString();
		//String path = "<img src=\"file://" + filePath + "\" alt=\"\"/>";
		return filePath;

	}


	/**
	 * This method used to send text to the text fields
	 * @return boolean
	 * @author ankit
	 * @return
	 * @since 12/04/2023
	 */
	public boolean sendKeys(WebElement element, String textToEnter, String strtext) {
		try {
			element.sendKeys(textToEnter);
			Thread.sleep(2000);
			test.log(LogStatus.PASS,strtext + " text is inserted successfully");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL,"Exception while inserting " + strtext);
			return false;
		}
	}
	
	public boolean elementexists(WebElement element, String strtext) {
		try {
			
			if(element.getText().equals(strtext)){
			test.log(LogStatus.PASS,"Text"+strtext + " text matches");
			return true;}
			else {
				test.log(LogStatus.PASS,"Text"+strtext + " text doesn't match");
			return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL,"Exception while inserting " + strtext);
			return false;
		}
	}

	public boolean selectElement(WebElement dropDown, String text, String strtext) {
		Select dr= new Select(dropDown);
		try {
			dr.selectByVisibleText(text);
			test.log(LogStatus.PASS,strtext+"Selected sucecessfully");
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL,"Exception while inserting " + strtext);
			return false;
		}
	}
	/**
	 * This method used to click the button
	 * @return boolean
	 * @author ankit
	 * @return
	 * @since 12/04/2023
	 */
	public boolean click(WebElement element, String strtext) {
		try {
			element.click();
			test.log(LogStatus.PASS,strtext+" is clicked Successfully");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL,"Exception while clicking " + strtext);
			return false;
		}
	}
	/**
	 * This method used Launch Browser
	 * @return boolean
	 * @author ankit
	 * @return
	 * @since 12/04/2023
	 */
	public boolean launchWebBrowser() {
		try{
			this.driver = getWebDriver(config("browser"));
			PageFactory.initElements(driver, this);
			wait= new WebDriverWait(driver,Duration.ofSeconds(10));

			action= new Actions(driver);
			test.log(LogStatus.PASS,"Browser Opened Successfully");
		}catch(Exception e) {
			test.log(LogStatus.FAIL,"Browser could not be loaded because:\n"+e);
			return false;
		}
		return true;
	}

	/**
	 * This method used to return browser Details
	 * @return boolean
	 * @author ankit
	 * @return
	 * @since 12/04/2023
	 */
	public WebDriver getWebDriver(String browserName) {

		switch (browserName) {
		case "chrome":
			//System.setProperty("webdriver.chrome.driver","/home/ankitt/eclipse-workapce_one/Intro/chromedriver_linux64_new/chromedriver");
			//System.setProperty("org.uncommons.reportng.escape-output", "false");
			options= new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			
			driver = new ChromeDriver(options);
			driver.manage().window().maximize();
			return driver;

		case "firefox":
			//System.setProperty("webdriver.firefox.marionette",
				//	workDir + "\\src\\test\\resources\\Drivers\\geckodriver.exe");
			System.setProperty("org.uncommons.reportng.escape-output", "false");
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			return driver;

		default:
			throw new RuntimeException("Driver Exception");

		}

	}

	/**
	 * This method used to launch the WebApplication with "https:\\www.google.com"
	 * @return boolean
	 * @author ankit
	 * @return
	 * @throws Throwable 
	 * @since 12/04/2023
	 */
	public boolean launchWebsite() throws Throwable{
		try {
			driver.navigate().to(config("URL"));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			test.log(LogStatus.PASS,"Application Launched Successfully");
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL,test.addScreenCapture(takeScreenshot())+"Exception while Launching application");
			return false;
		}
		return true;
	}


	/**
	 * This method used to login to application
	 * @return boolean
	 * @author ankit
	 * @return
	 * @since 12/04/2023
	 */
	public boolean login(String username, String password) {

		try {
			click(btnAccount,"Clicked on Account Btn");
			sendKeys(txtUserName, username, "User Name TextField");
			click(btnNextnSignIn, "Next on Password page");
			sendKeys(txtPassword, password, "Password Field");
			click(btnNextnSignIn, "Sigin on Login Page");
			boolean check = elementexists(textIntermmitentPage, "Stay signed in?");
			if(check==true) {
				click(btnDecline,"No on intermittent page");
			}
			wait.until(ExpectedConditions.visibilityOf(btnProfile));

			
			test.log(LogStatus.PASS,test.addScreenCapture(takeScreenshot())+" Login Successful");
			
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}


	/**
	 * This is to navigate to Job Opening Page
	 * @return boolean
	 * @author ankit
	 * @return
	 * @since 12/04/2023
	 */
	/*Commenting because of non usage of code
	 * public boolean clickOnLink() {
		try {
			wait.until(ExpectedConditions.visibilityOf(linkRecruitment));
			click(linkRecruitment,"Recruitment Module");
			wait.until(ExpectedConditions.visibilityOf(linkJobOpenings));
			test.addScreenCapture(takeScreenshot());

			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}*/
}

