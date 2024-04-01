package mantis.tests;

import mantis.common.CommonFunctions;
import org.junit.jupiter.api.Test;

public class JamesTest extends TestBase {

    @Test
    void canCreateUser(){
        app.jamesCli().addUser(CommonFunctions.randomEmailLocalhost(8),"password");
    }
}
