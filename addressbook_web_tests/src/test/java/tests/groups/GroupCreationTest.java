package tests.groups;

import model.GroupData;
import org.junit.jupiter.api.Test;
import tests.TestBase;

public class GroupCreationTest extends TestBase {


    @Test
    public void canCreateGroup() {
        app.groups().createGroup(new GroupData("Test group", "test group header", "test group footer"));
    }

    @Test
    public void canCreateGroupWithEmptyName() {
        app.groups().createGroup(new GroupData());
    }
    @Test
    public void canCreateGroupWithNameOnly() {
        app.groups().createGroup(new GroupData().withName("some name"));
    }
}
