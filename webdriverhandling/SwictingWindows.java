package webdriverhandling;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SwictingWindows {

	public static void main(String[] args) {
		
		System.getProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.get("https://demo.automationtesting.in/Windows.html");
		
		driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[1]/ul/li[1]/a")).click();//open newtabbed windows
		driver.findElement(By.xpath("//*[@id=\"Tabbed\"]/a/button")).click();//clicK button
		
		//System.out.println(driver.getTitle());
		
		Set <String> s=driver.getWindowHandles();
		
		for(String i:s)
		{
			//System.out.println(i);
			String t=driver.switchTo().window(i).getTitle();
			//System.out.println(t);
			if(t.contains("Selenium"))
			{
				driver.close();
			}
			
		}
		//driver.close();
	}

}
