package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupCreationTest extends TestBase{


    @Test
    public void canCreateGroup() {
        app.openGroupsPage();
        app.createGroup(new GroupData("Test group", "test group header", "test group footer"));
    }

    @Test
    public void canCreateGroupWithEmptyName() {
        app.openGroupsPage();
        app.createGroup(new GroupData());
    }
    @Test
    public void canCreateGroupWithNameOnly() {
        app.openGroupsPage();
        var emptyGroup = new GroupData();
        var groupWithName = emptyGroup.withName("some name");
        app.createGroup(groupWithName);
    }
}
