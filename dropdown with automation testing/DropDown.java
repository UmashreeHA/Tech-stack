package dropdown;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class DropDown {

	public static void main(String[] args) {
		
		System.getProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://testautomationpractice.blogspot.com");
		
		driver.manage().window().maximize();
		
		WebElement element=driver.findElement(By.id("colors"));
		Select se= new Select(element);
		
		List originalList= new ArrayList();
		
		List <WebElement>options=se.getOptions();
		
		for(WebElement e:options)
		{
			originalList.add(e.getText());
		}
		System.out.println("OriginalList: "+originalList);
		
		List tempList=new ArrayList();
		
		tempList=originalList;
		System.out.println("before sorting: "+ tempList);
		
		Collections.sort(tempList);
		System.out.println("after sorting: "+ tempList);
		System.out.println("after sorting originallist: "+ originalList);
		
		if(originalList == tempList)
		{
			System.out.println("dropdown sorted");
		}
		else
		{
			System.out.println("dropdown not sorted");
		}
	}

}
