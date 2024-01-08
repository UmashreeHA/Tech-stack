package actionsclass;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class DragAndDrop {

	public static void main(String[] args) {
		System.getProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		
		driver.get("http://dhtmlgoodies.com/scripts/drag-drop-custom/demo-drag-drop-3.html");
		
		Actions act = new Actions(driver);
		
		WebElement oslo=driver.findElement(By.xpath("//*[@id=\"box1\"]"));
		WebElement norway= driver.findElement(By.xpath("//*[@id=\"box101\"]"));
		act.clickAndHold(oslo).moveToElement(norway).release().build().perform();
		
		WebElement seoul=driver.findElement(By.xpath("//*[@id=\"box5\"]"));
		WebElement korea= driver.findElement(By.xpath("//*[@id=\"box105\"]"));
		act.clickAndHold(seoul).moveToElement(korea).release().build().perform();
		
		WebElement washing=driver.findElement(By.xpath("//*[@id=\"box3\"]"));
		WebElement us= driver.findElement(By.xpath("//*[@id=\"box103\"]"));
		act.clickAndHold(washing).moveToElement(us).release().build().perform();
		
		WebElement rome=driver.findElement(By.xpath("//*[@id=\"box6\"]"));
		WebElement italy= driver.findElement(By.xpath("//*[@id=\"box106\"]"));
		//act.clickAndHold(rome).moveToElement(italy).release().build().perform();
		act.dragAndDrop(rome, italy).build().perform();
		
		WebElement copa=driver.findElement(By.xpath("//*[@id=\"box4\"]"));
		WebElement denmark= driver.findElement(By.xpath("//*[@id=\"box104\"]"));
		act.dragAndDrop(copa, denmark).build().perform();
		
		WebElement madrid=driver.findElement(By.xpath("//*[@id=\"box7\"]"));
		WebElement spain= driver.findElement(By.xpath("//*[@id=\"box107\"]"));
		act.dragAndDrop(madrid, spain).build().perform();
		
		WebElement stockholm=driver.findElement(By.xpath("//*[@id=\"box2\"]"));
		WebElement sweden= driver.findElement(By.xpath("//*[@id=\"box102\"]"));
		act.dragAndDrop(stockholm, sweden).build().perform();
		
		driver.close();
	}

}
