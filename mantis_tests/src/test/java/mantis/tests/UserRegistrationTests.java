package mantis.tests;

import mantis.common.CommonFunctions;
import mantis.model.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class UserRegistrationTests extends TestBase {
    public static List<String> usernameProvider() {
        var result = new ArrayList<>(List.of(CommonFunctions.randomString(8)));
        return result;
    }

    @ParameterizedTest
    @MethodSource("usernameProvider")
    void canRegisterUser(String username) {
        var email = String.format("%s@localhost", username);
        var password = "password";
        app.jamesCli().addUser(email, password);
        app.browserHelper().signUp(username, email);
        var messages = app.mail().receive(email, password, Duration.ofSeconds(10));
        var url = CommonFunctions.extractUrl(messages.get(0).content());
        app.browserHelper().openLink(url);
        app.browserHelper().setPassword(username, password);
        app.http().login(username, password);
        Assertions.assertTrue(app.http().isLoggedIn());
    }

    @ParameterizedTest
    @MethodSource("usernameProvider")
    void canRegisterUserApi(String username) {
        var email = String.format("%s@localhost", username);
        var password = "password";
        app.jamesApi().addUser(email, password);
        app.browserHelper().signUp(username, email);
        var messages = app.mail().receive(email, password, Duration.ofSeconds(10));
        var url = CommonFunctions.extractUrl(messages.get(0).content());
        app.browserHelper().openLink(url);
        app.browserHelper().setPassword(username, password);
        app.http().login(username, password);
        Assertions.assertTrue(app.http().isLoggedIn());
    }

    @ParameterizedTest
    @MethodSource("usernameProvider")
    void canRegisterUserRestApi(String username) {
        var email = String.format("%s@localhost", username);
        var password = "password";
        app.jamesApi().addUser(email, password);
        app.rest().signUp(new UserData()
                .withName(username)
                .withEmail(email)
                .withPassword(password));
        var messages = app.mail().receive(email, password, Duration.ofSeconds(10));
        var url = CommonFunctions.extractUrl(messages.get(0).content());
        app.browserHelper().openLink(url);
        app.browserHelper().setPassword(username, password);
        app.http().login(username, password);
        Assertions.assertTrue(app.http().isLoggedIn());
    }
}
