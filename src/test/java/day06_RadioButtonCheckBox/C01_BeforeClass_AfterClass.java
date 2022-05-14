package day06_RadioButtonCheckBox;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class C01_BeforeClass_AfterClass {
    /*
    beforeClass ve AfterClass notsyonlari sadece static methodlar icin calisir
     */
    static WebDriver driver;
    @BeforeClass
    public static void ssetUp(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }
    @AfterClass
    public static void tearDown() {
        driver.close();
    }
    @Before
    public void before(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }
    @After
    public void after() {
        driver.close();
    }
@Test
    public void test1(){
        driver.get("https://www.amazon.com");
    System.out.println("test1 calisti");
}
    @Test
    public void test2(){
        driver.get("https://www.facebook.com");
        System.out.println("test2 calisti");
    }@Test
    public void test3(){
        driver.get("https://www.loreal.com");
        System.out.println("test3 calisti");
    }@Test
    public void test4(){
        driver.get("https://www.gucci.com");
        System.out.println("test calisti");
    }
}
