package stepDefinitions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;

public class Steps extends TestBase{
	
	
	
	@Before
    public void setup() throws IOException
    {

        //Logger steps
        logger= Logger.getLogger("nopcommerce");
        PropertyConfigurator.configure("Log4j.properties");
        logger.setLevel(Level.DEBUG);
        //Logger steps done

        //Loading Config.properties file steps
        configProp=new Properties();
        FileInputStream configPropfile = new FileInputStream("config.properties");
        configProp.load(configPropfile);
        //Loading Config.properties file is done


        String br=configProp.getProperty("browser");

        if (br.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver",configProp.getProperty("firefoxpath"));
            driver = new FirefoxDriver();
        }

        else if (br.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver",configProp.getProperty("chromepath"));
            driver = new ChromeDriver();
        }

    }


	@Given("User Launch firefox browser")
	public void user_Launch_firefox_browser() {
		
		logger.info("********* Launching browser***************");
		lp = new LoginPage(driver);
	}

	@When("User opens URL {string}")
	public void user_opens_URL(String url) {
		
		logger.info("********* Launching url***************");
		driver.get(url);
		driver.manage().window().maximize();
	    
	}

	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_Email_as_and_Password_as(String email, String password) {
		
		logger.info("********* Entering user info***************");
		lp.setUserName(email);
		lp.setPassword(password);
	    
	}

	@When("Click on Login")
	public void click_on_Login() throws InterruptedException{
		
		
		logger.info("********* Click on Login Buttton***************");
		lp.clickLogin();
		Thread.sleep(5000);
	    
	}

	@Then("Page Title should be {string}")
	public void page_Title_should_be(String expectedttl){
	    
		if(driver.getPageSource().contains("Login was unsuccessful"))
        {
           logger.info("*********Login Failed ***************");
           driver.close();
           Assert.assertTrue(false);
        }
        else
        {
            logger.info("*********Login successfull ***************");
            Assert.assertEquals(expectedttl, driver.getTitle());
        }
       
	}

	@When("User click on Log out link")
	public void user_click_on_Log_out_link() throws InterruptedException {
		
		
		logger.info("********* Click on Logout Button***************");
		lp.clickLogout();
	    Thread.sleep(3000);
	}

	@Then("close browser")
	public void close_browser() {
		
		logger.info("********* closing browser***************");
	driver.quit();	
	}

	
	//<==============================Add Customers Steps=================================>
	
	@Then("User can view Dashboard")
	public void user_can_view_Dashboard() {
	    
		logger.info("********* Adding Customer Scenario started ***************");
		addCust = new AddCustomerPage(driver);
		
		logger.info("********* user can see Dashboard ***************");
		Assert.assertEquals("Dashboard / nopCommerce administration",addCust.getPageTitle());
	}

	@When("User click on customers Menu")
	public void user_click_on_customers_Menu() throws InterruptedException {
		
		Thread.sleep(4000);
		logger.info("********* Click on Customer Menu ***************");
		addCust.clickOnCustomersMenu();
	    
	}

	@When("click on customers Menu Item")
	public void click_on_customers_Menu_Item() throws InterruptedException {
	    
		Thread.sleep(4000);
		
		logger.info("********* Click on Customer menu ***************");
		addCust.clickOnCustomersMenuItem();;
		
	}

	@When("click on Add new button")
	public void click_on_Add_new_button() {
		
		logger.info("********* Click on Add New Customer ***************");
		addCust.clickOnAddnew();
		
	}

	@Then("User can view Add new customer page")
	public void user_can_view_Add_new_customer_page() {
		
		
		logger.info("********* User Can view New Customer Page ***************");
		Assert.assertEquals("Add a new customer / nopCommerce administration", addCust.getPageTitle());
	}

	@When("User enter customer info")
	public void user_enter_customer_info() throws InterruptedException {
		
		
		logger.info("********* Providing Customer details ***************");
		String email = randomestring() + "@gmail.com";
        addCust.setEmail(email);
        addCust.setPassword("test123");
        // Registered - default
        // The customer cannot be in both 'Guests' and 'Registered' customer roles
        // Add the customer to 'Guests' or 'Registered' customer role
        addCust.setCustomerRoles("Guest");
        Thread.sleep(3000);

        addCust.setManagerOfVendor("Vendor 2");
        addCust.setGender("Male");
        addCust.setFirstName("Jayul");
        addCust.setLastName("Patel");
        addCust.setDob("05/09/1996"); // Format: MM/DD/YYY
        addCust.setCompanyName("QA Automation");
        addCust.setAdminContent("This is for Practice.........");
	   
	}

	@When("click on Save button")
	public void click_on_Save_button() {
	   
		addCust.clickOnSave();
	}

	@Then("User can view confirmation message {string}")
	public void user_can_view_confirmation_message(String message) {
		
		logger.info("********* Add customer validation ***************");
	    
		Assert.assertTrue(driver.findElement(By.tagName("body")).getText()
                .contains("The new customer has been added successfully"));	
	}
	
		
}
