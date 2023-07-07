import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {
    String city = DataGenerator.generateCity();
    String firstDate = DataGenerator.generateDate(4, "dd.MM.yyyy");
    String secondDate = DataGenerator.generateDate(7, "dd.MM.yyyy");
    String name = DataGenerator.generateName();
    String phoneNumber = DataGenerator.generatePhoneNumber();

    @Test
    void shouldDeliveryCard() {
        open("http://localhost:9999");

        $("[data-test-id=city] input").setValue(city);
        $("[data-test-id=date] input").doubleClick().sendKeys(firstDate);
        $("[data-test-id=name] input").setValue(name);
        $("[data-test-id=phone] input").setValue(phoneNumber);
        $(withText("Успешно")).shouldBe(Condition.hidden);
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=success-notification]").shouldBe(Condition.visible, Duration.ofSeconds(40));
        $("[data-test-id=success-notification]").shouldHave(Condition.text("Успешно!\n" +
                "Встреча успешно запланирована на " + firstDate)).shouldBe(Condition.visible);
        $("[data-test-id=date] input").doubleClick().sendKeys(secondDate);
        $(".button").click();
        $("[data-test-id=replan-notification]").shouldBe(Condition.visible, Duration.ofSeconds(40));
        $("[data-test-id=replan-notification]").shouldHave(Condition.text(
                "У вас уже запланирована встреча на другую дату. Перепланировать?")).shouldBe(Condition.visible, Duration.ofSeconds(40));
        $("[data-test-id=replan-notification] button").click();
        $("[data-test-id=success-notification]").shouldBe(Condition.visible, Duration.ofSeconds(40));
        $("[data-test-id=success-notification]").shouldHave(Condition.text("Успешно!\n" +
                "Встреча успешно запланирована на " + secondDate)).shouldBe(Condition.visible);
    }
}