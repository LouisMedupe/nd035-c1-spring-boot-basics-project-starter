package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SignupTests {
    @LocalServerPort
    private int port;

    private WebDriver webdriver;
    private WebDriverWait webDriverWait;

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void beforeEach() {
        this.webdriver= new ChromeDriver();
        this.webDriverWait = new WebDriverWait(webdriver, 5);
    }

    @AfterEach
    public void afterEach() {
        if (this.webdriver != null) {webdriver.quit();}
    }

    @Test
    public void SignupTests() {

        // Test Signup
        webdriver.get("http://localhost:" + this.port + "/signup");
        webDriverWait.until(ExpectedConditions.titleContains("Sign Up"));

        // Fill data
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputFirstName")));
        WebElement inputFirstName = webdriver.findElement(By.id("inputFirstName"));
        inputFirstName.click();
        inputFirstName.sendKeys("Louis");



        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputLastName")));
        WebElement inputLastName = webdriver.findElement(By.id("inputLastName"));
        inputLastName.click();
        inputLastName.sendKeys("Medupe");


        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
        WebElement inputUsername = webdriver.findElement(By.id("inputUsername"));
        inputUsername.click();
        inputUsername.sendKeys("Admin");

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputPassword")));
        WebElement inputPassword = webdriver.findElement(By.id("inputPassword"));
        inputPassword.click();
        inputPassword.sendKeys("Louis123");


        // Attempt to sign up.
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signup-submit-button")));
        WebElement buttonSignUp = webdriver.findElement(By.id("signup-submit-button"));
        buttonSignUp.click();

        webdriver.get("http://localhost:" + this.port + "/login");
        webDriverWait.until(ExpectedConditions.titleContains("Login"));

        Assertions.assertEquals("Login", webdriver.getTitle());
        // Fill Data
        webdriver.findElement(By.id("inputUsername")).sendKeys("Admin");
        webdriver.findElement(By.id("inputPassword")).sendKeys("Louis123");

        // Submit the form
          webdriver.findElement(By.id("login-submit-button")).click();


    }
}
