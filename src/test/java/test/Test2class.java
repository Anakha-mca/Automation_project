package test;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import page.Page2class;

public class Test2class {
	public WebDriver driver;
	@BeforeTest
	public void setup()
	{
		driver=new ChromeDriver();
	}
	@BeforeMethod
	public void url()
	{
		driver.get("https://www.amazon.in");
		driver.manage().window().maximize();
	}
	@Test
	public void test1() throws Exception 
	{
		Page2class ob=new Page2class(driver);
		ob.logo_checking();
		ob.mouse_over();
		ob.mouseover();
		ob.textverification();
		ob.contain_verification();
		ob.full_scnshot();
		ob.single_scnshot();
		ob.scrolldown();
		ob.window_handling();
		ob.brokelink_checking();
	}
	@AfterTest
    public void teardown() {
        if (driver != null) {
            driver.getCurrentUrl();
        }
    }
	
}
