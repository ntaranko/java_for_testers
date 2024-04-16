package tests.groups;

import io.qameta.allure.Allure;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import java.util.ArrayList;
import java.util.Random;

public class GroupRemovalTests extends TestBase {

    @Test
    public void canRemoveGroup() {
        Allure.step("Checking precondition", step -> {
            if (app.groups().getCount() == 0) {
                app.groups().createGroup(
                        new GroupData("", "Test group", "test group header", "test group footer"));
            }
        });
        var oldGroups = app.groups().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        Allure.step("Removing group", step -> {
            app.groups().removeGroup(oldGroups.get(index));
        });
        var newGroups = app.groups().getList();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.remove(index);
        Allure.step("Validating result", step -> {
            Assertions.assertEquals(newGroups, expectedList);
        });
    }

    @Test
    void canRemoveAllGroupsAtOnes() {
        Allure.step("Checking precondition", step -> {
            if (app.groups().getCount() == 0) {
                app.groups().createGroup(
                        new GroupData("", "Test group", "test group header", "test group footer"));
            }
        });
        Allure.step("Removing all groups", step -> {
            app.groups().removeAllGroups();
        });
        Allure.step("Validating result", step -> {
            Assertions.assertEquals(0, app.groups().getCount());
        });
    }

    @Test
    public void canRemoveGroupHbm() {
        Allure.step("Checking precondition", step -> {
            if (app.hbm().getGroupCount() == 0) {
                app.hbm().createGroup(
                        new GroupData("", "Test group", "test group header", "test group footer"));
            }
        });
        var oldGroups = app.hbm().getGroupList();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        Allure.step("Removing group", step -> {
            app.groups().removeGroup(oldGroups.get(index));
        });
        var newGroups = app.hbm().getGroupList();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.remove(index);
        Allure.step("Validating result", step -> {
            Assertions.assertEquals(newGroups, expectedList);
        });
    }

    @Test
    void canRemoveAllGroupsAtOnesHbm() {
        Allure.step("Checking precondition", step -> {
            if (app.hbm().getGroupCount() == 0) {
                app.hbm().createGroup(
                        new GroupData("", "Test group", "test group header", "test group footer"));
            }
        });
        Allure.step("Removing all groups", step -> {
            app.groups().removeAllGroups();
        });
        Allure.step("Validating result", step -> {
            Assertions.assertEquals(0, app.hbm().getGroupCount());
        });
    }
}
