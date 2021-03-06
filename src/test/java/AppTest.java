import org.junit.*;
import static org.junit.Assert.*;
import static org.fluentlenium.core.filter.FilterConstructor.*;

import java.util.ArrayList;

import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();
  public WebDriver getDefaultDriver() {
      return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
      goTo("http://localhost:4567/");
      assertThat(pageSource()).contains("My Address Book");
  }

  @Test
  public void contactIsCreatedTest() {
    goTo("http://localhost:4567/");
    fill("#firstName").with("Daren");
    fill("#lastName").with("Schaad");
    fill("#birthday").with("01/23/1981");
    submit(".btn");
    assertThat(pageSource()).contains("Daren Schaad");
  }
}
