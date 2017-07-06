package selenium_webdriver;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;





public class EmailRegistrationTest {

	
public WebDriver driver;
private WebElement login;
private WebElement password;
private String result;
private WebElement error;
int number_check;
int result_sucsess_check;
	

	@BeforeClass //выполняется до запуска всех тестов
	public  void Init() 
	 {
		System.setProperty("webdriver.chrome.driver","C:\\Program Files (x86)\\Eclipse\\Driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 }
	



    @Test //ввод некорректных символов в поле Почтовый ящик
	public  void ErrorSymbolsLogin ()
	{
    	number_check = 0;
		result_sucsess_check = 0;
    	driver.get("https://id.rambler.ru/account/registration");
		String[] value= {"..","qw..","qwer..ty","--","qw--","qwer--ty","--","qw--","qwer--ty",
						"__","qw__","qwer__ty",".qwerty", "qwerty.","-qwerty","qwerty-","_qwerty",
						"qwerty_","qwerty@","qw!","qw#","qw$","qw&","qw'","qw*","qw+",
						"qw/","qw=","qw?","qw^","qw`","qw{","qw}","qw~","qw(",
						"qw)","qw,","qw:","qw;","qw<","qw>","qw[",
						"qw]","qw|", };
		
		login = driver.findElement(By.cssSelector("input[name='login.username']"));
		password = driver.findElement(By.cssSelector("input[name='password.main']"));
		login.click();
		password.click();
		error = driver.findElement(By.cssSelector("#root > div > div > form > section:nth-child(3) > div > div.src-components-Status-styles--error--RfBOb > div.src-components-Status-styles--message--cGbII"));
		result = error.getText();
		
		if(result.equals("Недопустимый логин"))
		{
			result_sucsess_check = result_sucsess_check+1;
			System.out.println("Sucsess empty check");
		} else
		{
			System.out.println("Failed empty check");
		}
			
		number_check = number_check+1;
		login.click();
		
		for (int i=0;i<value.length;i++)
		{
			
			login.sendKeys(value[i]);
			error = driver.findElement(By.cssSelector("#root > div > div > form > section:nth-child(3) > div > div.src-components-Status-styles--error--RfBOb > div.src-components-Status-styles--message--cGbII"));
			result = error.getText();
			if(result.equals("Недопустимый логин"))
			{
				result_sucsess_check = result_sucsess_check+1;
				System.out.println("Sucsess check " + value[i]);
			} else
			{
				System.out.println("Failed check " + value[i]);
			}
			
			number_check = number_check+1;
			login.clear();	
		}
		Assert.assertEquals(result_sucsess_check,number_check);
	}

	
	@Test // успешный логин
	public void SucsessLogin ()
	{
		driver.get("https://id.rambler.ru/account/registration");
		String[] value= { "surnamename65","65surnamename","65surnamename56", "SURNAMEsurname"};
		login = driver.findElement(By.cssSelector("input[name='login.username']"));
		password = driver.findElement(By.cssSelector("input[name='password.main']"));
		login.click();
		
		for (int i=0;i<value.length;i++)
		{
			login.sendKeys(value[i]);
			password.click();
			try {
				error = driver.findElement(By.xpath("//*[@id='root']/div/div/form/section[3]/div/div[1]/div[2]"));
				System.out.println("Failed test for value value" + value[i]);
				}
			catch (NoSuchElementException e) 
					{
						System.out.println("Sucsess!"); 
					}
		login.click();
		login.clear();
		
		}	
		
	}
	
	
	@Test //пользователь уже существует
	public void ExistUser()
	{
		driver.get("https://id.rambler.ru/account/registration");
		login = driver.findElement(By.cssSelector("input[name='login.username']"));
		password = driver.findElement(By.cssSelector("input[name='password.main']"));
		login.click();
		login.sendKeys("test65surname");
		password.click();
		error = driver.findElement(By.cssSelector("#root > div > div > form > section:nth-child(3) > div > div.src-components-Status-styles--error--RfBOb > div.src-components-Status-styles--message--cGbII"));
		result = error.getText();
		if(result.equals("1"))
		{
			password.click();
			error = driver.findElement(By.cssSelector("#root > div > div > form > section:nth-child(3) > div > div.src-components-Status-styles--error--RfBOb > div.src-components-Status-styles--message--cGbII"));
			result = error.getText();
			Assert.assertEquals("Пользователь уже существует!", result);
		} else
		{
			System.out.println("Failed check");
			//return;
		}
		
}
	
	
	
	@Test //допустимая длина Логина
	public void MoreLessSymbolsLogin()
	{
		number_check = 0;
		result_sucsess_check = 0;
		driver.get("https://id.rambler.ru/account/registration");
		login = driver.findElement(By.cssSelector("input[name='login.username']"));
		password = driver.findElement(By.cssSelector("input[name='password.main']"));
		login.click();
		login.sendKeys("123456789012345678901234567890qwerty");
		password.click();
		error = driver.findElement(By.cssSelector("#root > div > div > form > section:nth-child(3) > div > div.src-components-Status-styles--error--RfBOb > div.src-components-Status-styles--message--cGbII"));
		result = error.getText();
		if(result.equals("Логин должен быть от 3 до 31 символов"))
		{
			result_sucsess_check = result_sucsess_check+1;
			System.out.println("Sucsess check");
		} else
		{
			System.out.println("Failed check " + result);
		}
		number_check = number_check+1;
		login.click();
		login.clear();
		login.sendKeys("21");
		password.click();
		error = driver.findElement(By.cssSelector("#root > div > div > form > section:nth-child(3) > div > div.src-components-Status-styles--error--RfBOb > div.src-components-Status-styles--message--cGbII"));
		result = error.getText();
		if(result.equals("Логин должен быть от 3 до 31 символов"))
		{
			result_sucsess_check = result_sucsess_check+1;
			System.out.println("Sucsess check - ");
		} else
		{
			System.out.println("Failed check " + result);
		}
		
		number_check = number_check+1;
		Assert.assertEquals(result_sucsess_check,number_check);
	}
	

	
	 @AfterClass //выполняется после запуска всех тестов
	 public void End ()
	 {
		 driver.quit();	 
	 }
	

}





