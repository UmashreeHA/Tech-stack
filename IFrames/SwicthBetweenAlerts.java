package alert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SwicthBetweenAlerts {

	public static void main(String[] args) {
		
		System.getProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://demo.automationtesting.in/Alerts.html");
		
		//Alert With OK
		driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[1]/ul/li[1]/a")).click();
		//alertokbutton
		driver.findElement(By.xpath("//*[@id=\"OKTab\"]/button")).click();
		//swicth alert button
		System.out.println(driver.switchTo().alert().getText());
		driver.switchTo().alert().accept();
		
		
	}

}
