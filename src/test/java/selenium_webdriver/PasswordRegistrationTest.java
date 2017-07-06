package selenium_webdriver;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class PasswordRegistrationTest {

private WebDriver driver;
private WebElement login;
private WebElement password_concide;
private WebElement password;
private String result;
private WebElement error;
int number_check;
int result_sucsess_check;

	

	
	@BeforeClass //выполняется до запуска всех тестов
	public  void Init() 
	{
		//System.setProperty("webdriver.chrome.driver","C:\\Program Files (x86)\\Eclipse\\Driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}


	
	@Test //ввод скрытого Пароля
	public void PasswordHidden()
	{
		driver.get("https://id.rambler.ru/account/registration");
		password = driver.findElement(By.cssSelector("input[name='password.main']"));
		password.click();
		password.sendKeys("qw*sss");
		result = password.getAttribute("type");
		Assert.assertEquals("password", result);			
	}
	
	
	@Test //ввод скрытого Пароля подтверждения
	public void ConfirmPasswordHidden()
	{
		driver.get("https://id.rambler.ru/account/registration");
		password_concide = driver.findElement(By.cssSelector("input[name='password.confirm']"));
		password_concide.click();
		password_concide.sendKeys("qw*sss");
		result = password_concide.getAttribute("type");
		Assert.assertEquals("password", result);			
	}
		
	
	@Test //отображение вводимого Пароля подтверждения
	public void ConfirmPasswordOpen()
	{
		driver.get("https://id.rambler.ru/account/registration");
		WebElement open = driver.findElement(By.cssSelector("#root > div > div > form > div:nth-child(4) > section:nth-child(2) > div > div > div.src-components-Status-styles--field--1mG76 > div > button"));
		open.click();
		password_concide = driver.findElement(By.cssSelector("input[name='password.confirm']"));
		password_concide.click();
		password_concide.sendKeys("qw*sss");
		result = password_concide.getAttribute("type");
		Assert.assertEquals("text", result);			
	}
	
	
	@Test //отображение вводимого Пароля
	public void PasswordOpen()
	{
		driver.get("https://id.rambler.ru/account/registration");
		WebElement open = driver.findElement(By.cssSelector("#root > div > div > form > div:nth-child(4) > section:nth-child(1) > div > div:nth-child(3) > div > div > button"));
		open.click();
		password = driver.findElement(By.cssSelector("input[name='password.main']"));
		password.click();
		password.sendKeys("qw*sss");
		result = password.getAttribute("type");
		Assert.assertEquals("text", result);			
	}
	
	
	@Test //совпадение пароля и пароля подтверждения
	public void PasswordConcide()
	{
		driver.get("https://id.rambler.ru/account/registration");
		password_concide = driver.findElement(By.cssSelector("input[name='password.confirm']"));
		password = driver.findElement(By.cssSelector("input[name='password.main']"));
		password.click();
		password.sendKeys("qw*sss");
		password_concide.click();
		password_concide.sendKeys("qw*sss");
		error = driver.findElement(By.cssSelector("#root > div > div > form > div:nth-child(4) > section:nth-child(2) > div > div > div.src-components-Status-styles--message--cGbII"));
		result = error.getText();
		Assert.assertEquals("Введено верно", result);			
	}
			
	
	
	@Test //несовпадение Пароля и Пароля подтверждения
	public void PasswordnotConcide()
	{
		number_check = 0;
		result_sucsess_check = 0;
		driver.get("https://id.rambler.ru/account/registration");
		password_concide = driver.findElement(By.cssSelector("input[name='password.confirm']"));
		password = driver.findElement(By.cssSelector("input[name='password.main']"));
		password.click();
		password.sendKeys("qw*sss");
		password_concide.click();
		password.click();
		error = driver.findElement(By.cssSelector("#root > div > div > form > div:nth-child(4) > section:nth-child(2) > div > div > div.src-components-Status-styles--message--cGbII"));
		result = error.getText();
		
		if(result.equals("Пароли не совпадают"))
		{
			result_sucsess_check = result_sucsess_check+1;
			System.out.println("Sucsess empty check");
		} else
		{
			System.out.println("Failed empty check");
		}
			
		number_check = number_check+1;
		password_concide.click();
			
		String[] value= {"qw*sss1","Qw*sss1","QWsSs1","1234567890"};
			
		for (int i=0;i<value.length;i++)
		{
				
			password_concide.sendKeys(value[i]);
			result = error.getText();
				
			if(result.equals("Пароли не совпадают"))
			{
				result_sucsess_check = result_sucsess_check+1;
				System.out.println("Sucsess check " + value[i]);
			} else
			{
				System.out.println("Failed check " + value[i]);
			}
		
			password_concide.clear();
			number_check = number_check+1;	
		}
			
		Assert.assertEquals(result_sucsess_check,number_check);
	}
		
			
	
	@Test //допустимая длина поля Пароль
	public void MoreLessSymbolsPassword()
	{
		number_check = 0;
		result_sucsess_check = 0;
		driver.get("https://id.rambler.ru/account/registration");
		WebElement login = driver.findElement(By.cssSelector("input[name='login.username']"));
		password = driver.findElement(By.cssSelector("input[name='password.main']"));
		password.click();
		password.sendKeys("123456789012345678901234567890qwertyqwerty");
		login.click();
		WebElement error = driver.findElement(By.cssSelector("#root > div > div > form > div:nth-child(4) > section:nth-child(1) > div > div.src-components-Status-styles--error--RfBOb > div.src-components-Status-styles--message--cGbII"));
		result = error.getText();
		
		if(result.equals("Пароль должен содержать от 6 до 32 символов"))
		{
			result_sucsess_check = result_sucsess_check+1;
			System.out.println("Sucsess check");
		} else
		{
			System.out.println("Failed check");
		}
	
		number_check = number_check+1;
		password.click();
		password.clear();
		password.sendKeys("21768");
		login.click();
		result = error.getText();
		
		if(result.equals("Пароль должен содержать от 6 до 32 символов"))
		{
			result_sucsess_check = result_sucsess_check+1;
			System.out.println("Sucsess check");
		} else
		{
			System.out.println("Failed check");
		}
		
		number_check = number_check+1;
		Assert.assertEquals(result_sucsess_check,number_check);
	}

	
	
	@Test //ввод некорректных символов в поле Пароль
	public  void ErrorSymbolsPassword ()
	{
		number_check = 0;
		result_sucsess_check = 0;
		driver.get("https://id.rambler.ru/account/registration");	
		String[] value= {"#","'","/","=","?","`","{","}","~",
		",",":",";","<",">","[","]","|"};
		login = driver.findElement(By.cssSelector("input[name='login.username']"));
		password = driver.findElement(By.cssSelector("input[name='password.main']"));
		password.click();
		login.click();
		error = driver.findElement(By.cssSelector("#root > div > div > form > div:nth-child(4) > section:nth-child(1) > div > div.src-components-Status-styles--error--RfBOb > div.src-components-Status-styles--message--cGbII"));
		result = error.getText();
		
		if(result.equals("Пароль должен содержать от 6 до 32 символов"))
		{
			result_sucsess_check = result_sucsess_check+1;
			System.out.println("Sucsess empty check");
		} else
		{
			System.out.println("Failed empty check");
		}
		
		number_check = number_check+1;
		password.click();
		password.sendKeys("тест");
		result = error.getText();
		if(result.equals("Вы вводите русские буквы"))
		{
			result_sucsess_check = result_sucsess_check+1;
			System.out.println("Sucsess empty check");
		} else
		{
			System.out.println("Failed empty check");
		}
		
		number_check = number_check+1;
		password.clear();
		
		for (int i=0;i<value.length;i++)
		{
			password.sendKeys(value[i]);
			result = error.getText();
			if(result.equals("Символ " + "\"" + value[i] + "\"" + " не поддерживается. Можно использовать символы ! @ $ % ^ & * ( ) _ - +"))
			{
				result_sucsess_check = result_sucsess_check+1;
				System.out.println("Sucsess check " + value[i]);
			} else
			{
				System.out.println("Failed check " + value[i] );
			}
			
			number_check = number_check+1;
			password.clear();
		}
		Assert.assertEquals(result_sucsess_check,number_check);
	}




	@Test //успешный Пароль
	public void SucsessPassword()
	{
		number_check = 0;
		result_sucsess_check = 0;
		driver.get("https://id.rambler.ru/account/registration");
		String[] value= {"qw$sss","qw&sss","qw*sss","qw+sss",
						"qw!sss","qw@sss","qw%sss","qw^sss","qw(sss","qw)sss","qw_sss","qw-sss","TEST65Surname"};
		login = driver.findElement(By.cssSelector("input[name='login.username']"));
		password = driver.findElement(By.cssSelector("input[name='password.main']"));
		password.click();
		login.click();
		password.click();
		error = driver.findElement(By.cssSelector("#root > div > div > form > div:nth-child(4) > section:nth-child(1) > div > div.src-components-Status-styles--error--RfBOb > div.src-components-Status-styles--message--cGbII"));
		for (int i=0;i<value.length;i++)
		{
			password.sendKeys(value[i]);
			//login.click();	
			result = error.getText();
			if(result.equals("Пароль средней сложности"))	
			{
				System.out.println("Sucsess check");
				result_sucsess_check = result_sucsess_check+1;
			} else 
			if (result.equals("Сложный пароль"))
			{
				result_sucsess_check = result_sucsess_check+1;
				System.out.println("Sucsess check");
			} else
			{
				System.out.println("Failed check" + value[i]);
			}
		
			number_check = number_check+1;
			password.clear();

		}
		
		Assert.assertEquals(result_sucsess_check,number_check);
}
	

	
	 @AfterClass //выполняется после запуска всех тестов
	 public void End()
	 {
		 driver.quit(); 
	 }
	 
}

