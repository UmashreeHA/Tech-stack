package webdriverhandling;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Register {

	private static final String Select = null;

	public static void main(String[] args) {
		
		System.getProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.get("https://demo.automationtesting.in/Register.html");
		
		driver.findElement(By.xpath("//*[@id=\"basicBootstrapForm\"]/div[1]/div[1]/input")).sendKeys("pavan");
		driver.findElement(By.xpath("//*[@id=\"basicBootstrapForm\"]/div[1]/div[2]/input")).sendKeys("doddmani");
		driver.findElement(By.xpath("//*[@id=\"basicBootstrapForm\"]/div[2]/div/textarea")).sendKeys("Channagiri");
		driver.findElement(By.xpath("//*[@id=\"eid\"]/input")).sendKeys("pavan@gmaiil.com");
		driver.findElement(By.xpath("//*[@id=\"basicBootstrapForm\"]/div[4]/div/input")).sendKeys("9620350000");
		driver.findElement(By.xpath("//*[@id=\"basicBootstrapForm\"]/div[5]/div/label[1]/input")).click();
		driver.findElement(By.id("checkbox1")).click();
		driver.findElement(By.id("checkbox2")).click();
		
		
		WebElement lan=driver.findElement(By.xpath("/html/body/section/div/div/div[2]/form/div[7]/div/multi-select/div[1]"));
		
		Select t=new Select(lan);
		t.selectByValue("English");
		
		WebElement skill=driver.findElement(By.id("Skills"));
		Select s=new Select(skill);
		s.selectByValue("Java");
		//System.out.println(s.getOptions().size());
		
		/*WebElement coun=driver.findElement(By.xpath(//*[@id="select2-country-container"]));
		Select c=new Select(coun);
		c.selectByVisibleText("India");*/
		
		driver.findElement(By.xpath("//*[@id=\"select2-country-container\"]")).click();
		
		driver.findElement(By.xpath("//*[@id=\"yearbox\"]")).sendKeys("2000");
		driver.findElement(By.xpath("//*[@id=\"basicBootstrapForm\"]/div[11]/div[2]/select")).sendKeys("December");
		driver.findElement(By.xpath("//*[@id=\"daybox\"]")).sendKeys("20");
		
		driver.findElement(By.id("firstpassword")).sendKeys("pavan@1234");
		driver.findElement(By.id("secondpassword")).sendKeys("pavan@1234");
		
		driver.findElement(By.id("Button1")).click();
		
		driver.close();
	}
}
