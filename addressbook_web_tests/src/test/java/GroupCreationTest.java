import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupCreationTest extends TestBase{


    @Test
    public void canCreateGroup() {
        openGroupsPage();
        createGroup(new GroupData("Test group", "test group header", "test group footer"));
    }

    @Test
    public void canCreateGroupWithEmptyName() {
        openGroupsPage();
        createGroup(new GroupData());
    }
    @Test
    public void canCreateGroupWithNameOnly() {
        openGroupsPage();
        var emptyGroup = new GroupData();
        var groupWithName = emptyGroup.withName("some name");
        createGroup(groupWithName);
    }
}
