package actionsclass;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class MouseRightClick {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		System.getProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://swisnl.github.io/jQuery-contextMenu/demo.html");
		
		Actions act= new Actions(driver);
		
		WebElement button=driver.findElement(By.xpath("/html/body/div[1]/section/div/div/div/p/span")); //right click me 
		act.contextClick(button).build().perform();
		
		driver.findElement(By.xpath("/html/body/ul/li[3]")).click(); //click on copy
		
		System.out.println(driver.switchTo().alert().getText());
		Thread.sleep(6000);
		driver.switchTo().alert().accept(); 
		
		
	}
	
}
