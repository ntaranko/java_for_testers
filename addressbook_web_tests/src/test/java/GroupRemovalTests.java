import org.junit.jupiter.api.Test;

public class GroupRemovalTests extends TestBase{

    @Test
    public void canRemoveGroup() {
        openGroupsPage();
        if (!isGroupPresent()){
            createGroup("Test group", "test group header", "test group footer");
        }
        removeGroup();
    }

}
