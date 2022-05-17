package day06;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class BeforeClassAfterClass {
    //before class ve afterclass methodlari sadece static methodlar icin calisabilir
    //before ve after a gore daha hizlidir, neredeyse yari yariya :)
   static WebDriver driver;//bunu burda yazmazsak tum metodlarda calsimaz, h
    //her method icin ayri ayri yazmak gerekecek

    @BeforeClass
    public static void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
//ustteki 4 satirin her methodda calismasini istiyorum
    }

    @AfterClass
    public static void tearDown() {
        //driver.close();
    }

    @Test
    public  void test01() {
        //a. Verilen web sayfasına gidin https://the-internet.herokuapp.com/checkboxes
         driver.get("https://the-internet.herokuapp.com/checkboxes");
        //b. Checkbox1 ve checkbox2 elementlerini locate edin.
        WebElement checkbox1=driver.findElement(By.xpath("//input[@type='checkbox'][1]"));
        WebElement checkbox2=driver.findElement(By.xpath("//input[@type='checkbox'][2]"));

        //c. Checkbox1 seçili değilse onay kutusunu tıklayın
        if (!checkbox1.isSelected()) {
            checkbox1.click();
        }

        //d. Checkbox2 seçili değilse onay kutusunu tıklayın
        if (!checkbox2.isSelected()) {
            checkbox2.click();
        }
    }

    @Test
    public  void test02() {
        //-https://www.facebook.com adresine gidin
        driver.get("https://www.facebook.com");
         //-Cookies’i kabul edin
        driver.findElement(By.xpath("//button[@value='1'][2]")).click();
         //-“Create an Account” button’una basin
        driver.findElement(By.xpath("//a[@class='_42ft _4jy0 _6lti _4jy6 _4jy2 selected _51sy']")).click();
         //-“radio buttons” elementlerini locate edin
        WebElement kadin = driver.findElement(By.xpath("(//input[@type='radio'])[1]"));
        WebElement erkek = driver.findElement(By.xpath("(//input[@type='radio'])[2]"));
        WebElement özel = driver.findElement(By.xpath("(//input[@type='radio'])[3]"));

        //-Secili degilse cinsiyet butonundan size uygun olani secin
        if (!özel.isSelected()){
            özel.click();
        }
    }

    @Test
    public void test03() {
        // urlin oldugunu https://www.facebook.com test edin
        //https://www.amazon.com
        driver.get("https://www.amazon.com");
        //Message(+assert kismi) kismi equals false verdigi zaman terminalde gözükür.
        String expectedUrl = "https://www.facebook.com";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals("De url's zijn niet dezelfde", actualUrl, expectedUrl);


    }
}
