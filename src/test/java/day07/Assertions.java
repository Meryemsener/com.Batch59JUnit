package day07;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;

public class Assertions {

    static WebDriver driver;

    @BeforeClass
    public static void ssetUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @AfterClass
    public static void tearDown() {
         driver.quit();
    }

    @Test
    public void testA() {

      /*
    amazon ana sayfaya gidin
    3 farkli test method'u olusturarak asagidaki gorevleri yapin
    1- Url'in amazon icerdigini test edin
    2- title'in facebook icermedigini test edin
    3- sol ust kosede amazon logosunun gorundugunu test edin
     */
        // 1- Url'in amazon icerdigini test edin
        String arananKelime="amazon";
        String actualUrl= driver.getCurrentUrl();
        Assert.assertTrue(actualUrl.contains(arananKelime));
        // 2- title'in facebook icermedigini test edin
        String istenmeyenKelime="facebook";
        String actualTitle=driver.getTitle();
        Assert.assertFalse(actualTitle.contains(istenmeyenKelime));
        // 3- sol ust kosede amazon logosunun gorundugunu test edin
        WebElement logoElementi= driver.findElement(By.id("nav-logo-sprites"));
        Assert.assertTrue(logoElementi.isDisplayed());
    }


@Test
        public  void testb() {

    //○ titleTest => Sayfa başlığının “Rest” içermediğini(contains) test edin
    String istenmeyenKelime = "Rest";
    String actualTitle = driver.getTitle();
    Assert.assertFalse(actualTitle.contains(istenmeyenKelime));

    //      ○ logoTest => BestBuy logosunun görüntülendigini test edin
    WebElement logoElementi = driver.findElement(By.xpath("(//img[@class='logo'])[1]"));
    Assert.assertTrue(logoElementi.isDisplayed());
//      ○ FrancaisLinkTest => Fransizca Linkin görüntülendiğini test edin
    WebElement francaisElementi = driver.findElement(By.xpath("//*[text()='Français']"));
    Assert.assertTrue(francaisElementi.isDisplayed());
}

   @Test
   public void testC() {
//http://automationpractice.com/index.php sayfasina gidelim
       driver.get("http://automationpractice.com/index.php");
       //Sign in butonuna basalim
       driver.findElement(By.xpath("//a[@class='login']")).click();
       //Email kutusuna @isareti olmayan bir mail yazip enter’a bastigimizda
       //   “Invalid email address” uyarisi ciktigini test edelim
       WebElement mailKutusu= driver.findElement(By.xpath("//input[@id='email_create']"));
       mailKutusu.sendKeys("mehmetgmail.com" + Keys.ENTER);
       WebElement uyariYazisiElementi= driver.findElement(By.xpath("//*[text()='Invalid email address.']"));
       Assert.assertTrue(uyariYazisiElementi.isDisplayed());
   }


    public void dropdown() throws InterruptedException {
        driver.get("https://www.amazon.com");
        // dropdown'dan bir option secmek icin 3 adim vardir
        // 1- dropdown'i locate edelim
        WebElement dropDownMenu= driver.findElement(By.xpath("//select[@id='searchDropdownBox']"));
        // 2- bir Select objesi olusturup
        //    parametre olarak bir onceki adimda locate ettigimiz ddm'yu girelim
        Select select=new Select(dropDownMenu);
        // 3- Dropdown'da var olan option'lardan istedigimiz bir taneyi secelim
        select.selectByVisibleText("Books");
        // select.selectByIndex(5);
        // select.selectByValue("search-alias=stripbooks-intl-ship");
        // arama kutusuna Java yazdiralim
        WebElement aramaKutusu= driver.findElement(By.id("twotabsearchtextbox"));
        aramaKutusu.sendKeys("Java" + Keys.ENTER);
        WebElement sonucYazisiElementi=driver.findElement(By.xpath("(//div[@class='a-section a-spacing-small a-spacing-top-small'])[1]"));
        String sonucYazisiStr=sonucYazisiElementi.getText();
        String arananKelime="Java";
        Assert.assertTrue(sonucYazisiStr.contains(arananKelime));
        Thread.sleep(5000);


    }
    @Test
    public void dropDownMenu(){
        driver.get("https://www.amazon.com");
        WebElement ddm= driver.findElement(By.xpath("//select[@id='searchDropdownBox']"));
        Select select= new Select(ddm);
        select.selectByVisibleText("Books");
        // bir dropdown ile calisiyorken, son secilen optiona'a ulasmak isterseniz
        // select.getFirstSelectedOption() method'unu kullanmalisiniz
        // bu method bize WebElement dondurur,
        // uzerindeki yaziyi yazdirmak icin getText() unutulmamalidir
        System.out.println(select.getFirstSelectedOption().getText());
        // dropdown'daki opsiyonlarin toplam sayisinin
        //        28 oldugunu test edin
        List<WebElement> optionList = select.getOptions();
        int actualOptionSayisi= optionList.size();
        int expectedOptionsayisi=28;
        Assert.assertEquals(expectedOptionsayisi,actualOptionSayisi);

    }
    @Test
    public void testF() {
        // ● https://the-internet.herokuapp.com/dropdown adresine gidin.
        driver.get("https://the-internet.herokuapp.com/dropdown");
        //  1.Index kullanarak Seçenek 1’i (Option 1) seçin ve yazdırın
        WebElement ddm= driver.findElement(By.xpath("//select[@id='dropdown']"));
        Select select=new Select(ddm);
        select.selectByIndex(1);
        System.out.println(select.getFirstSelectedOption().getText());
        //  2.Value kullanarak Seçenek 2'yi (Option 2) seçin ve yazdırın
        select.selectByValue("2");
        System.out.println(select.getFirstSelectedOption().getText());
        //  3.Visible Text(Görünen metin) kullanarak Seçenek 1’i (Option 1) seçin ve yazdırın
        select.selectByVisibleText("Option 1");
        System.out.println(select.getFirstSelectedOption().getText());
        //  4.Tüm dropdown options'i yazdırın
        List<WebElement> tumOpsiyonlar= select.getOptions();

       for (WebElement each: tumOpsiyonlar
       ) {
                    System.out.println(each.getText());
                }
        //  5. Dropdown’un boyutunu bulun, Dropdown’da 4 öğe varsa konsolda True ,
        //  degilse False yazdırın.
        int dropdownBoyut= tumOpsiyonlar.size();

        if (dropdownBoyut==4){
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }

    }

