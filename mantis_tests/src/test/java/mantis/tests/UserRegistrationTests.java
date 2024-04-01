package mantis.tests;

import org.junit.jupiter.api.Test;

public class UserRegistrationTests extends TestBase{
    @Test
    void canRegisterUser(String username){
        var email = String.format("%s@localhost", username);
        // JamesHelper: create new user on mail server
        //Браузер: открыть браузер и заполнить форму создания и отправить письмо
        // MailHelper: ждем почту
        //извлечь ссылку из письма
        //Браузер: возвращаемся в браузер, проходим по ссылке и завершаем регистрацию
        // HttpSessionHelper: проверка: пользователь может залогиниться

    }
}
