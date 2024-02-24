package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupRemovalTests extends TestBase{

    @Test
    public void canRemoveGroup() {
        app.openGroupsPage();
        if (!app.isGroupPresent()){
            app.createGroup(new GroupData("Test group", "test group header", "test group footer"));
        }
        app.removeGroup();
    }

}
