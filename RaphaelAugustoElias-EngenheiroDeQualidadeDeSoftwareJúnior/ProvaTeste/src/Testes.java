import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Testes {
	
	private WebDriver driver;
	
	@BeforeAll
	public static void beforeAll() {
		System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
	}
	
	@BeforeEach
	public void beforeEach() {
		driver = new ChromeDriver();
		driver.get("https://advantageonlineshopping.com");
	}
	
	@AfterEach
	public void afterEach() {
		this.driver.quit();
	}
	
	@Test
	public void validaEspecificacaoDoProduto() {
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("see_offer_btn")));
		driver.findElement(By.id("see_offer_btn")).click();

		
		Assert.assertTrue(true);
	}
	
	@Test
	public void validaAlteracaoDeCorNoProdutoNoCarrinho() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("see_offer_btn")));
		driver.findElement(By.id("see_offer_btn")).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("span[class=\"bunny productColor ng-scope BLACK\"]")));
		driver.findElement(By.cssSelector("span[class=\"bunny productColor ng-scope BLACK\"]")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("save_to_cart")));
		driver.findElement(By.name("save_to_cart")).click();
		
		//----------------------------
		wait.until(ExpectedConditions.elementToBeClickable(By.id("menuCart")));
		driver.findElement(By.id("menuCart")).click();
		
		
		Assert.assertTrue(driver.findElement(By.cssSelector("span[title]")).getAttribute("title").equals("BLACK"));
	}
	
	@Test
	public void validaPaginaDeCheckout() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.id("see_offer_btn")));
		driver.findElement(By.id("see_offer_btn")).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.name("save_to_cart")));
		driver.findElement(By.name("save_to_cart")).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.id("menuCart")));
		driver.findElement(By.id("menuCart")).click();
		
		Assert.assertTrue(true);
	}
	
	@Test
	public void validaPesquisaDeProduto() {
		
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("search")));
		driver.findElement(By.id("search")).click();
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("autoComplete")));
		driver.findElement(By.id("autoComplete")).sendKeys("HP PRO TABLET 608 G1");
		driver.findElement(By.id("autoComplete")).sendKeys(Keys.RETURN);
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("18")));
		driver.findElement(By.id("18")).click();
		
		
		Assert.assertTrue(driver.getCurrentUrl().contains("https://advantageonlineshopping.com/#/product/18"));
	}
	
	@Test
	public void validaRemocaoDeProdutoDoCarrinho() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.id("see_offer_btn")));
		driver.findElement(By.id("see_offer_btn")).click();
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.name("save_to_cart")));
		driver.findElement(By.name("save_to_cart")).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.id("menuCart")));
		driver.findElement(By.id("menuCart")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()=\"REMOVE\"]")));
		driver.findElement(By.xpath("//a[text()=\"REMOVE\"]")).click();
		
		
		Assert.assertTrue(driver.findElement(By.xpath("//a [text()=\"CONTINUE SHOPPING\"]")) != null);
	}

}
