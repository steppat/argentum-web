package br.com.caelum.argentum.teste;

import static org.hamcrest.Matchers.containsString;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class GeraGraficoTest {
	
        private static final String URL = "http://argentum-web.cloudfoundry.com/";
		private static final String ERRO_TITULO = "n‹o pode estar vazio";
		private FirefoxDriver driver;

		@Before
   		public void setUp() throws Exception {
			FirefoxProfile profile = new FirefoxProfile();
		    profile.setPreference("intl.accept_languages", "pt-BR");
           	 driver = new FirefoxDriver(profile);
   		}

        @After
        public void tearDown() {
            driver.close();
        }

        @Test
        public void testAoGerarSemTituloUmaMensagemEhApresentada() {
        		driver.get(URL);
        		driver.findElement(By.id("titulo")).sendKeys("");
        		driver.findElement(By.id("form")).submit();
        		WebElement validationErrorElement = driver.findElement(By.id("titulo.errors"));
        		Assert.assertThat(validationErrorElement.getText(), containsString(ERRO_TITULO));
        }

}
