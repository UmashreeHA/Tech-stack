package alert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AlertWithText {

	public static void main(String[] args) throws InterruptedException {
		System.getProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://demo.automationtesting.in/Alerts.html");
		
		//alert text
		driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[1]/ul/li[3]/a")).click();
		
		//alert text button
		driver.findElement(By.xpath("//*[@id=\"Textbox\"]/button")).click();
		
		//1st click
		driver.switchTo().alert().sendKeys("Alfred");
		driver.switchTo().alert().accept();
		
		String exptext="Hello Automation Testing user How are you today";
		
		String acttext = driver.findElement(By.xpath("//*[@id=\"demo1\"]")).getText();
		
		if(exptext.equals(acttext)==true)
		{
			System.out.println("Passed");
		}
		else
		{
			System.out.println("Failed");
		}
		
		//2nd click
		
		Thread.sleep(6000);
		driver.findElement(By.xpath("//*[@id=\"Textbox\"]/button")).click();
		
		driver.switchTo().alert().sendKeys("Umashree");
		driver.switchTo().alert().accept();
		//driver.close();
		
	}

}
