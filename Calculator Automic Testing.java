package build;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Calculator {

	public static void main(String[] args) throws IOException {
		
		System.getProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();                                                                                                                                                                                      
		
		driver.get("https://www.moneycontrol.com/fixed-income/calculator/state-bank-of-india-sbi/fixed-deposit-calculator-SBI-BSB001.html");
		
		driver.manage().window().maximize();
		
		FileInputStream file = new FileInputStream("C:\\Users\\umash\\eclipse-workspace\\interest.xlsx");
		
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		
		int rowcount = sheet.getLastRowNum();
		
		for(int i=1;i<rowcount;i++)
		{
			XSSFRow row= sheet.getRow(i);
			int princ=(int)row.getCell(0).getNumericCellValue();
			
			//String var=row.getCell(0).getStringCellValue();
			
			//XSSFCell principal=row.getCell(0);
			//int princ=(int)principal.getNumericCellValue();
			
			
			XSSFCell roi=row.getCell(1);
			int rateofinterest=(int)roi.getNumericCellValue();
			
			XSSFCell period=row.getCell(2);
			int per=(int)period.getNumericCellValue();
			
			XSSFCell Frequency=row.getCell(3);
			String Freq=Frequency.getStringCellValue();
			
			XSSFCell MaturityValue=row.getCell(4);
			int Exp_mvalue=(int)MaturityValue.getNumericCellValue();
		
			driver.findElement(By.xpath("//*[@id=\"principal\"]")).sendKeys(String.valueOf(princ));
		    driver.findElement(By.xpath("//*[@id=\"interest\"]")).sendKeys(String.valueOf(rateofinterest));
		    driver.findElement(By.xpath("//*[@id=\"tenure\"]")).sendKeys(String.valueOf(per));
		    
		    Select periodcombo = new Select(driver.findElement(By.id("tenurePeriod")));
		    periodcombo.selectByVisibleText("year(s)");
		    
		    Select frequency = new Select(driver.findElement(By.id("frequency")));
		    frequency.selectByVisibleText(Freq);
		    
		    driver.findElement(By.xpath("//*[@id=\"fdMatVal\"]/div[2]/a[1]/img")).click();
		    
		    String actual_mvalue=driver.findElement((By.xpath(".//*[@id=\"resp_matval\"]/strong"))).getText();
		    
		    if((Double.parseDouble(actual_mvalue))== Exp_mvalue)
		    {
		    	System.out.println("Test passed");
		    }
		    else
		    {
		    	System.out.println("Test failed");
		    }
		    
		    
		    //driver.findElement(By.id("principal")).clear();
		    //driver.findElement(By.id("interest")).clear();
		    //driver.findElement(By.id("tenure")).clear();
		    
		    driver.findElement(By.xpath("//*[@id=\"fdMatVal\"]/div[2]/a[2]/img")).click();
		} 								
		driver.close();
		driver.quit();
		
	}

}
