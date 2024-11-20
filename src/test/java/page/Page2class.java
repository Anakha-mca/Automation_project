package page;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Page2class {
	WebDriver driver;
	WebElement logo;
	List<WebElement> li;
	
	public Page2class(WebDriver driver)
	{
		this.driver=driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        logo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='nav-logo-sprites']")));
        li=driver.findElements(By.tagName("a"));
	}
	
	public void logo_checking()
	{
		if(logo.isDisplayed())
		{	System.out.println("Logo is displayed");
		}
		else
		{	System.out.println("Logo is not displayed");
		}
	}
	public void mouse_over()
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement hoverElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='icp-nav-flyout']")));

        Actions act = new Actions(driver);
        act.moveToElement(hoverElement).perform();

        WebElement targetElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"nav-flyout-icp\"]/div[2]/a[6]/span/span[1]")));
        targetElement.click();
	}
	public void mouseover()
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement mouseover = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='icp-nav-flyout']")));
      
        Actions act = new Actions(driver);
        act.moveToElement(mouseover).perform();

        WebElement target = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"nav-flyout-icp\"]/div[2]/a[1]/span/i")));
        target.click();
	}
	
	public void textverification()
	{
		String actual=driver.getTitle();
		System.out.println(actual);
		String expected="Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
		if(actual.equals(expected)) {
			System.out.println("Title verification passed");
		}
		else
		{
			System.out.println("Title verification failed");
		}
	}
	public void contain_verification()
	{
		String con=driver.getPageSource();
		if(con.contains("mobiles"))
		{
			System.out.println("Content verification passed");
		}
		else
		{
			System.out.println("Content verification failed");
		}
	}
	public void brokelink_checking() throws Exception
	{
		 List<WebElement> links = driver.findElements(By.tagName("a")); 
		    System.out.println("Total links found: " + links.size());
		    int count = 0;

		    for (int i = 0; i < links.size(); i++) {
		        
		        links = driver.findElements(By.tagName("a"));
		        WebElement linkElement = links.get(i);
		        String link = linkElement.getAttribute("href");

		        if (link != null && !link.isEmpty()) {
		            verify(link);
		            count++;
		            if (count == 5) {
		                System.out.println("Checked 5 links and stopped.");
		                break;
		            }
		        } else {
		            System.out.println("Empty or null link found, skipping...");
		        }
		    }
	}
	public void verify(String link) {
		try
		{
			URL ob=new URL(link);
			HttpURLConnection con=(HttpURLConnection)ob.openConnection();
			con.connect();
			if(con.getResponseCode()== 200)
			{
				System.out.println("valid url");
			}
			else if(con.getResponseCode()==404)
			{
				System.out.println("broken link");
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}	
	}
	
	public void full_scnshot() throws Exception
	{
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(src, new File("E:Amazonpage.png"));
		System.out.println("full scnshot");
	}
	
	public void single_scnshot() throws Exception 
	{
		WebElement sign=driver.findElement(By.xpath("//*[@id=\"nav-link-accountList\"]/div"));
		File srcc=sign.getScreenshotAs(OutputType.FILE);
		FileHandler.copy(srcc, new File("./screenshot/sign.png"));
		System.out.println("single element scnshot taked");
	}
	
	public void scrolldown()
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		System.out.println("scroll down");
	}
	
	
	public void window_handling()
	{
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	
	driver.findElement(By.xpath("//*[@id=\"twotabsearchtextbox\"]")).sendKeys("airpod");
	driver.findElement(By.xpath("//*[@id=\"nav-search-submit-button\"]")).click();
	
	String actualtitle=driver.getTitle();
	System.out.println(actualtitle);
	String expectedtitle="Amazon.in : airpod";
	Assert.assertEquals(actualtitle, expectedtitle);
	
	String parentwindow=driver.getWindowHandle();
	driver.findElement(By.xpath("//*[@id=\"search\"]/div[1]/div[1]/div/span[1]/div[1]/div[3]/div/div/div/div/span/div/div/div/div[2]/div/div/div[1]/h2/a/span")).click();
	
	Set<String> allwindowhandle = driver.getWindowHandles();
	for(String handle:allwindowhandle)
	{
		if(!handle.equalsIgnoreCase(parentwindow))
		{
			driver.switchTo().window(handle);
			driver.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();
			driver.findElement(By.xpath("//*[@id=\"attachSiNoCoverage\"]/span/input")).click();
			driver.findElement(By.xpath("//*[@id=\"sw-gtc\"]/span/a")).click();
			
			WebElement  quality =driver.findElement(By.xpath("//*[@id=\"quantity\"]"));
			Select qty=new Select(quality);
		    qty.selectByValue("2");
		    
		    WebElement checkbox=driver.findElement(By.xpath("//*[@id=\"sc-buy-box-gift-checkbox\"]"));
			if(checkbox.isSelected())
			{
				System.out.println("Checkbox is checked");
			}
			else
			{
				System.out.println("Checkbox is not checked");
			}
		}
		driver.switchTo().window(parentwindow);
	}
	driver.navigate().back();
	}
}
