import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

public class gmailLogin {
	String workDir = System.getProperty("user.dir");
	WebDriver driver;
	XSSFWorkbook workbook;
    XSSFSheet sheet;
    XSSFCell cell;
	 
    
	@SuppressWarnings("deprecation")
	/*@Test
	public void f() throws IOException, InterruptedException {
		// Import excel sheet.
		File src = new File(workDir+"/testData/data.xlsx");
		// Load the file.
		FileInputStream fis = new FileInputStream(src);
		// Load he workbook.
		workbook = new XSSFWorkbook(fis);
		// Load the sheet in which data is stored.
		sheet = workbook.getSheetAt(0);
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			
			 * I have added test data in the cell A2 as "testemailone@test.com"
			 * and B2 as "password" Cell A2 = row 1 and column 0. It reads first
			 * row as 0, second row as 1 and so on and first column (A) as 0 and
			 * second column (B) as 1 and so on
			 

			// Import data for Email.
			cell = sheet.getRow(i).getCell(0);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell = sheet.getRow(i).getCell(0);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//input[@type='email'][@name='email']")).clear();
			driver.findElement(By.xpath("//input[@type='email'][@name='email']")).sendKeys(cell.getStringCellValue());
			 
			// Import data for password.
			cell = sheet.getRow(i).getCell(1);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.findElement(By.xpath("//input[@type='password'][@name='pass']")).clear();	         
			driver.findElement(By.xpath("//input[@type='password'][@name='pass']")).sendKeys(cell.getStringCellValue());
			Thread.sleep(2000);
			// To click on Login button
			driver.findElement(By.id("loginbutton")).click();
			// To click on Account settings dropdown
			driver.findElement(By.xpath("//div[text()='Account Settings']")).click();
			// To click on logout button
			driver.findElement(By.xpath("//text()[.='Log Out']/ancestor::span[1]")).click();
		}
	}*/
	 // To get data from dataprovider
	 @Test(dataProvider="testdatasetexcel")
	 public void fbLoginLogout(String email, String password) throws Exception{
	 
	 
	 // To clear the email field
	 driver.findElement(By.xpath("//input[@type='email'][@name='email']")).clear();
	 // To pass Email
	 driver.findElement(By.xpath("//input[@type='email'][@name='email']")).sendKeys(email);
	 // To clear password field
	 driver.findElement(By.xpath("//input[@type='password'][@name='pass']")).clear(); 
	 // To pass password
	 driver.findElement(By.xpath("//input[@type='password'][@name='pass']")).sendKeys(password);
	 // To click on Login button
	 Thread.sleep(3000);
	 }
	
	// @DataProvider passes data to test cases. Here I took 2 dimension array. 
	 @DataProvider(name="testdataset")
	 public Object[][] getData(){
	 // Create object with two paraments
	 // first parameter is row and second one is column
	 Object[][] data = new Object[2][2];
	 data[0][0] = "testemailone@gmail.com";
	 data[0][1] = "password";
	 
	 data[1][0] = "testemailtwo@test.com";
	 data[1][1] = "password";
	 
	 return data;
	 }
	 
	 @DataProvider(name="testdatasetexcel")
	public Object[][] getDataExcel() throws IOException {
		// Create object with two paraments
		// first parameter is row and second one is column
		File src = new File(workDir + "/testData/data.xlsx");
		// Load the file.
		FileInputStream fis = new FileInputStream(src);
		// Load he workbook.
		workbook = new XSSFWorkbook(fis);
		// Load the sheet in which data is stored.
		sheet = workbook.getSheetAt(0);
		int rowCount=sheet.getLastRowNum();
		Object[][] data = new Object[(rowCount-1)][2];
		for (int i = 1,j=0; i < rowCount; i++,j++) {
			cell = sheet.getRow(i).getCell(0);
			data[j][0] = cell.getStringCellValue();
			cell = sheet.getRow(i).getCell(1);
			data[j][1] = cell.getStringCellValue();
		}
		return data;
	}
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", workDir + "/drivers/chromedriver.exe");
		driver= new ChromeDriver();
		driver.get("https://www.facebook.com");
	    // To maximize the browser
	    driver.manage().window().maximize();
	    // implicit wait
	    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
