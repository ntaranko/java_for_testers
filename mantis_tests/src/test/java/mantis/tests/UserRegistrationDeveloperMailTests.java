package mantis.tests;

import mantis.common.CommonFunctions;
import mantis.model.DeveloperMailUser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;

public class UserRegistrationDeveloperMailTests extends TestBase {

    DeveloperMailUser user;

    @Test
    void canRegisterUserDeveloperMailApi() {
        var password = "password";
        user = app.developerMail().addUser();
        var email = String.format("%s@developermail.com", user.name());

        app.browserHelper().signUp(user.name(), email);
        var message = app.developerMail().receive(user, Duration.ofSeconds(10));
        var url = CommonFunctions.extractUrl(message);
        app.browserHelper().openLink(url);
        app.browserHelper().setPassword(user.name(), password);
        app.http().login(user.name(), password);
        Assertions.assertTrue(app.http().isLoggedIn());
    }

    @AfterEach
    void deleteMailUser() {
        app.developerMail().deleteUser(user);
    }
}
