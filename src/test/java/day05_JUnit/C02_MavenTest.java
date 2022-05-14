package day05_JUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.openqa.selenium.By.xpath;

public class C02_MavenTest {
    /*
     1. http://zero.webappsecurity.com sayfasina gidin
     2. Signin buttonuna tiklayin
     3. Login alanine  “username” yazdirin
     4. Password alanine “password” yazdirin
     5. Sign in buttonuna tiklayin (hata mesaji icin back tusuna tiklayin)
     6. Online Banking menusunden Pay Bills sayfasina gidin
     7. amount kismina yatirmak istediginiz herhangi bir miktari yazin
     8. tarih kismina “2020-09-10” yazdirin
     9. Pay buttonuna tiklayin
     10. “The payment was successfully submitted.” mesajinin ciktigini kontrol edin

     */
    public static void main(String[] args) {


        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        //1. http://zero.webappsecurity.com sayfasina gidin
        driver.get("http://zero.webappsecurity.com");
        //2. Signin buttonuna tiklayin
        driver.findElement(xpath("//button[@id='signin_button']")).click();
        //3. Login alanine  “username” yazdirin
        driver.findElement(xpath("//input[@id='user_login']")).sendKeys("username");
        //4. Password alanine “password” yazdirin
        driver.findElement(By.id("user_password")).sendKeys("password");
        //5. Sign in buttonuna tiklayin (hata mesaji icin back tusuna tiklayin
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        driver.navigate().back();
        //6. Online Banking menusunden Pay Bills sayfasina gidin
        driver.findElement(By.xpath("(//*[text()='Online Banking'])[1]")).click();
        driver.findElement(By.xpath("//span[@id='pay_bills_link']")).click();

        //7. amount kismina yatirmak istediginiz herhangi bir miktari yazin
        driver.findElement(By.xpath("//input[@id='sp_amount']")).sendKeys("1000");

        //8. tarih kismina “2020-09-10” yazdirin
        driver.findElement(By.xpath("//input[@id='sp_date']")).sendKeys("2020-09-1");
        //9. Pay buttonuna tiklayin
        driver.findElement(By.xpath("//input[@id='pay_saved_payees']")).click();
        //10. “The payment was successfully submitted.” mesajinin ciktigini kontrol edin
        if (driver.findElement(By.xpath("//*[text()='The payment was successfully submitted.']")).isDisplayed()) {
            System.out.println("Test PASSED");
        } else System.out.println("Test FAILED");
        driver.close();


    }
}