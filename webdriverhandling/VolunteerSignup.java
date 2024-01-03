package webdriverhandling;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class VolunteerSignup {

	public static void main(String[] args) {
		
		System.getProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.get("https://testautomationpractice.blogspot.com");

		driver.findElement(By.id("name")).sendKeys("pavan");
		driver.findElement(By.id("email")).sendKeys("pavan@gmail.com");
		driver.findElement(By.id("phone")).sendKeys("9907452746");
		driver.findElement(By.id("textarea")).sendKeys("nelmangala");
		
		WebElement coun=driver.findElement(By.id("country"));
		
		Select t=new Select(coun);
		
		t.selectByIndex(9);//index
		//t.selectByValue("india");//value
		//t.selectByVisibleText("India");//visible text
		
		System.out.println(t.getOptions().size());
		
		//radio button selection
		
		System.out.println(driver.findElement(By.id("male")).isSelected());
		driver.findElement(By.id("male")).click();
		
		//checkbox selection
		driver.findElement(By.id("monday")).click();
		driver.findElement(By.id("thursday")).click();
		driver.findElement(By.id("saturday")).click();
		
		
		//submit forms
		
		driver.findElement(By.id("RESULT_TextField-0")).sendKeys("pavan");
		driver.findElement(By.id("RESULT_RadioButton-1_1")).click();
		driver.findElement(By.id("RESULT_TextField-2")).sendKeys("12/27/2023");
	
		WebElement jb=driver.findElement(By.id("q3"));
		Select q=new Select(jb);
		
		q.selectByValue("Radio-0");
		
		driver.findElement(By.id("FSsubmit")).click();
		//https://demo.automationtesting.in/Register.html just use refresh
	}

}
