package alert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @author umash
 *
 */
/**
 * @author umash
 *
 */
public class AlertWithOkAndCancel {

	public static void main(String[] args) {
		System.getProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://demo.automationtesting.in/Alerts.html");
		

		//Alert With Okay and Cancel
		driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[1]/ul/li[2]/a")).click();
		
		//okayand cancel button
		driver.findElement(By.xpath("//*[@id=\"CancelTab\"]/button")).click();
		
		/*click  cancel
		System.out.println(driver.switchTo().alert().getText());
		driver.switchTo().alert().dismiss();*/
		
		//click Ok
		String Expval="You pressed Ok";
		
		
		driver.switchTo().alert().accept();
		
		String Actval= driver.findElement(By.xpath("//*[@id=\"demo\"]")).getText();
		
		if(Expval.equals(Actval)==true)
		{
			System.out.println("Test is passed");
		}
		
		String expcan="You Pressed Cancel";
	
		driver.findElement(By.xpath("//*[@id=\"CancelTab\"]/button")).click();
		 
		driver.switchTo().alert().dismiss();
		Actval= driver.findElement(By.xpath("//*[@id=\"demo\"]")).getText();
		
		if(expcan.equals(Actval)== true)
		{
			System.out.println("Test is cancel");
		}
		
		
	}

}
