//Aqui importamos las librerias.
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.*;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;


//Aqui comenzamos la classe Main donde se va ha estructurar todo el codigo
public class Main {

  public static void main(String[] args) {

    //Ponemos scanner y lo necesario para scriptear pero sobretodo lo que hara es poder iniciar firefox sin snap
    Scanner scan = new Scanner(System.in);
    System.out.println(System.getenv("PATH"));
    System.out.println(System.getenv("HOME"));
    //  System.out.println(System.getenv(""));

    System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
    // File pathBinary = new File("src/main/resources/firefox");
    //  FirefoxBinary firefoxBinary = new FirefoxBinary(pathBinary);
    // DesiredCapabilities desired = new DesiredCapabilities();
    FirefoxOptions options = new FirefoxOptions();
    // desired.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options.setBinary(firefoxBinary));
    WebDriver driver = new FirefoxDriver(options);
    // Con driver.get pondremos la Url de la pagina que queremos scriptear y asi cuando ejecutemos el programa se nos abrira esta pagina automaticamente.
    driver.get("https://genshin-impact.fandom.com/es/wiki/Wiki_Genshin_Impact");
    String title = driver.getTitle();

    //Empieza el csv
    FileWriter webcsv = null;
    try {
      webcsv = new FileWriter("paginas.csv", true);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    try {
      webcsv.write("titulo\n");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

// Aqui crearemos una funcion para que a la hora de abrir la pagina esta saca unas cookies entonces esto le clickea automaticamente.
    WebElement acceptButton = driver.findElement(By.className("NN0_TB_DIsNmMHgJWgT7U"));

    acceptButton.click();

// esta lista de webelement scriptea todo los nombres de los personajes de la pagina
    List<WebElement> cardLinks = driver.findElements(By.className("card-link"));


    for (WebElement cardLink : cardLinks) {
      System.out.println(cardLink.getText());
    }
    int entrar = 0;

    //Este while es un bucle para un switch que te permitira scriptear las siguientes opciones en el orden que quieras.
    while(entrar == 0){
      System.out.println("Que quieres scriptear");
      System.out.println("1- Personaje, 2- Región, 3- Arma, 4- Salir");
      int usuario = scan.nextInt();


      switch (usuario) {
        case 1:
          // En el caso 1 se scriptea el personaje y su descripción, historia y habilidades.
          driver.findElement(By.linkText("Shogun Raiden")).click();
          System.out.println("Title of page is: " + driver.getTitle());

          List<WebElement> ps = driver.findElements(By.tagName("p"));
          List<WebElement> his = driver.findElements(By.className("mw-collapsible"));

          for (WebElement p : ps) {
            System.out.println(p.getText());
          }
          for (WebElement historia : his) {
            System.out.println(historia.getText());
          }
          try {
            webcsv.write(driver.getTitle() + "\n");
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
          break;

        case 2:
          //En el caso 2 nos dirigimos hacia la pagina de la region pero debemos pasar por la del persoanje.
          // Scripteamos descripción y todos los datos de la region.
          driver.findElement(By.linkText("Shogun Raiden")).click();
          System.out.println("Title of page is: " + driver.getTitle());

          driver.get("https://genshin-impact.fandom.com/es/wiki/Inazuma");
          System.out.println("Title of page is: " + driver.getTitle());

          List<WebElement> ps2 = driver.findElements(By.tagName("p"));
          List<WebElement> his2 = driver.findElements(By.className("mw-collapsible"));

          for (WebElement p : ps2) {
            System.out.println(p.getText());
          }
          for (WebElement historia : his2) {
            System.out.println(historia.getText());
          }
          try {
            webcsv.write(driver.getTitle() + "\n");
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
          break;

        case 3:
          //Aqui hacemos lo mismo que en el caso 2 pero ahora con las armas que puede utilizar el personaje.
          driver.findElement(By.linkText("Shogun Raiden")).click();
          System.out.println("Title of page is: " + driver.getTitle());

          driver.get("https://genshin-impact.fandom.com/es/wiki/Lanza");
          System.out.println("Title of page is: " + driver.getTitle());

          List<WebElement> ps3 = driver.findElements(By.tagName("p"));
          List<WebElement> his3 = driver.findElements(By.className("mw-collapsible"));

          for (WebElement p : ps3) {
            System.out.println(p.getText());
          }
          for (WebElement historia : his3) {
            System.out.println(historia.getText());
          }
          try {
            webcsv.write(driver.getTitle() + "\n");
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
          break;
        // En este caso te permite salir del bucle.
        case 4:
          entrar++;
          break;
//Esta opción es por si no has utilizado ninguna de los otros casos.
        default:
          System.out.println("Esta opción no esta permitida");
          break;
      }
    }
    try {
      webcsv.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    driver.close();
  }

}


