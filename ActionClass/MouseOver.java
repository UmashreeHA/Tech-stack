package actionsclass;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class MouseOver {

	public static void main(String[] args) throws InterruptedException {
		System.getProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://demo.opencart.com");
		
		Actions act= new Actions(driver);
		
		WebElement lap=driver.findElement(By.xpath("//*[@id=\"narbar-menu\"]/ul/li[2]/a"));
		WebElement mac = driver.findElement(By.xpath("//*[@id=\"narbar-menu\"]/ul/li[2]/div/div/ul/li[1]/a"));
		WebElement win= driver.findElement(By.xpath("//*[@id=\"narbar-menu\"]/ul/li[2]/div/div/ul/li[2]/a"));
		
		act.moveToElement(lap).click().perform(); //mouseover to laptop
		act.moveToElement(mac).moveToElement(win).click().perform();//mouseover to macbook
		
		/*Thread.sleep(6000);
		act.moveToElement(lap).click().perform();
		act.moveToElement(win).click().perform(); //mouseover to windows*/
		
		/*driver.manage().window().maximize();
		
		WebElement op1=driver.findElement(By.xpath("/html/body/main/div[1]/nav/div[2]/ul/li[1]/a"));
		WebElement op2=driver.findElement(By.xpath("/html/body/main/div[1]/nav/div[2]/ul/li[1]/div/div/ul/li[2]/a"));
		
	
		act.moveToElement(op1).moveToElement(op2).click().perform();*/
		
	}

}
