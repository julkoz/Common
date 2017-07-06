package selenium_webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class EmailMessageTest {
	
public WebDriver driver;
private WebElement login;
private WebElement password;
int number_check;
int result_sucsess_check;
	


	@BeforeClass //выполняется до запуска всех тестов
	public void Init() 
	{
		System.setProperty("webdriver.chrome.driver","C:\\Program Files (x86)\\Eclipse\\Driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://mail.rambler.ru/");
	}
		

	@Test
	public void EmailCheck()
	{
		login = driver.findElement(By.name("login")); 
		login.sendKeys("test65surname");
		password = driver.findElement(By.name("password"));
		password.sendKeys("SurnameNameTest65");
		WebElement button_email = driver.findElement(By.xpath("//button[text()='Войти в почту']"));
		button_email.submit();
		WebElement name = driver.findElement(By.cssSelector("span.rambler-topline__user-name"));
		name.click();
		WebElement message = driver.findElement(By.cssSelector("a.rambler-topline-user-dropdown__mail"));
		String result =  message.getText();
			if(result.equals("test65surname@rambler.ru"))
			{
				System.out.println("No message!");
			}
			else
			{
				System.out.println("You have messages!");
			
			}		
	}	

	
	 @AfterClass //выполняется после запуска всех тестов
	 public void End()
	 	{
			 driver.quit(); 
	 	}	
	 
 }
