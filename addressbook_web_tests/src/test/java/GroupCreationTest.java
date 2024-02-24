import org.junit.jupiter.api.Test;

public class GroupCreationTest extends TestBase{


    @Test
    public void canCreateGroup() {
        openGroupsPage();
        createGroup("Test group", "test group header", "test group footer");
    }

    @Test
    public void canCreateGroupWithEmptyName() {
        openGroupsPage();
        createGroup("", "", "");
    }

}
