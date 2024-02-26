package tests.groups;

import model.GroupData;
import org.junit.jupiter.api.Test;
import tests.TestBase;

public class GroupRemovalTests extends TestBase {

    @Test
    public void canRemoveGroup() {
        if (!app.groups().isGroupPresent()){
            app.groups().createGroup(new GroupData("Test group", "test group header", "test group footer"));
        }
        app.groups().removeGroup();
    }
}
