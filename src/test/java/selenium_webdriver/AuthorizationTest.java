package selenium_webdriver;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//
public class AuthorizationTest {

	
//�������� ������ ����� - ������ - test65surname / SurnameNameTest65
private WebDriver driver;
private WebElement login;
private WebElement password;
int number_check;
int result_sucsess_check;


	@BeforeClass ////����������� �� ������� ���� ������
	public void Init() 
	{
		System.setProperty("webdriver.chrome.driver","C:\\Program Files (x86)\\Eclipse\\Driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
	}


	@Test(priority=1) //�������� �����������
	public void Sucsess()
	{
		
		driver.get("https://mail.rambler.ru/");
		login = driver.findElement(By.name("login")); 
		login.click();
		login.sendKeys("test65surname");
		password = driver.findElement(By.name("password"));
		password.click();
		password.sendKeys("SurnameNameTest65");
		WebElement button_email = driver.findElement(By.xpath("//button[text()='����� � �����']"));
		button_email.submit();
		WebElement name = driver.findElement(By.cssSelector("span.rambler-topline__user-name"));
		name.click();
		WebElement profile = driver.findElement(By.cssSelector("a.rambler-topline-user-dropdown__profile"));
		profile.click();
		WebElement result = driver.findElement(By.xpath("//span[@class='rid-contacts__contact'][@title='test65surname@rambler.ru']"));
		number_check = 0;
		result_sucsess_check = 0;
		String result_text = result.getText();
		if(result_text.equals("test65surname@rambler.ru"))
		{
			result_sucsess_check = result_sucsess_check+1;
			System.out.println("Sucsess check");
		} else
		{
			System.out.println("Failed check");
		}
		
		number_check = number_check+1;
		name = driver.findElement(By.cssSelector("span.rambler-topline__user-name"));
		name.click();
		WebElement logout = driver.findElement(By.cssSelector("a.rambler-topline-user-dropdown__logout"));
		logout.click();
		Assert.assertEquals(result_sucsess_check,number_check);	
	}
	

	@Test //������� ��������� ������ ��� �����������
	public void FailPassword()
	{
		driver.get("https://mail.rambler.ru/");
		login = driver.findElement(By.name("login"));
		login.click();
		login.sendKeys("test65surname");
		password = driver.findElement(By.name("password"));
		password.click();
		password.sendKeys("SurnameNameTest");
		WebElement button_email = driver.findElement(By.xpath("//button[text()='����� � �����']"));
		button_email.submit();
		WebElement error_password = driver.findElement(By.cssSelector("i.form-msg__content"));
		String result_text = error_password.getText();
		Assert.assertEquals("������������ ����� ��� ������", result_text);
	}


	@Test //������� ��������� ����� ��� �����������
	public void FailLogin()
	{
		driver.get("https://mail.rambler.ru/");
		login = driver.findElement(By.name("login"));
		login.click();
		login.sendKeys("testsurname");
		password = driver.findElement(By.name("password"));
		password.click();
		password.sendKeys("SurnameNameTest65");
		WebElement button_email = driver.findElement(By.xpath("//button[text()='����� � �����']"));
		button_email.submit();
		WebElement error_login = driver.findElement(By.cssSelector("i.form-msg__content"));
		String result_text = error_login.getText();
		Assert.assertEquals("������������ ����� ��� ������", result_text);
	}

	
	@Test //������� ��������� ����� � ������ ��� �����������
	public void FailLoginAndPassword()
	{
		driver.get("https://mail.rambler.ru/");
		login = driver.findElement(By.name("login"));
		login.click();
		login.sendKeys("testsurname");
		password = driver.findElement(By.name("password"));
		password.click();
		password.sendKeys("SurnameNameTest");
		WebElement button_email = driver.findElement(By.xpath("//button[text()='����� � �����']"));
		button_email.submit();
		WebElement error_loginandpassword = driver.findElement(By.cssSelector("i.form-msg__content"));
		String result_text = error_loginandpassword.getText();
		Assert.assertEquals("������������ ����� ��� ������", result_text);
	}

	 @AfterClass //����������� ����� ������� ���� ������
	 public void End()
	 {
		 driver.quit(); 
	 }
	
}
