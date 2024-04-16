package tests.groups;

import common.CommonFunctions;
import io.qameta.allure.Allure;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.Set;

public class GroupModificationTest extends TestBase {

    @Test
    void canModifyGroup() {
        Allure.step("Checking precondition", step -> {
            if (app.groups().getCount() == 0) {
                app.groups().createGroup(new GroupData("", "Test group", "test group header", "test group footer"));
            }
        });
        var oldGroups = app.groups().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        var testData = new GroupData().withName("modified name");
        Allure.step("Group modification", step -> {
            app.groups().modifyGroup(oldGroups.get(index), testData);
        });
        var newGroups = app.groups().getList();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.set(index, testData.withId(oldGroups.get(index).id()));
        Comparator<GroupData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareById);
        expectedList.sort(compareById);
        Allure.step("Validating result", step -> {
            Assertions.assertEquals(newGroups, expectedList);
        });
    }

    @Test
    void canModifyGroupHbm() {
        Allure.step("Checking precondition", step -> {
            if (app.groups().getCount() == 0) {
                app.groups().createGroup(new GroupData("", "Test group", "test group header", "test group footer"));
            }
        });
        var oldGroups = app.hbm().getGroupList();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        var testData = new GroupData().withName("modified name");
        Allure.step("Group modification", step -> {
            app.groups().modifyGroup(oldGroups.get(index), testData);
        });
        var newGroups = app.hbm().getGroupList();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.set(index, testData.withId(oldGroups.get(index).id()));
        Comparator<GroupData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareById);
        expectedList.sort(compareById);
        Allure.step("Validating result", step -> {
            Assertions.assertEquals(newGroups, expectedList);
        });
    }

    @Test
    void canModifyGroupHbmSet() {
        Allure.step("Checking precondition", step -> {
            if (app.groups().getCount() == 0) {
                app.groups().createGroup(new GroupData("", "Test group", "test group header", "test group footer"));
            }
        });
        var oldGroups = app.hbm().getGroupList();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        var testData = new GroupData().withName(CommonFunctions.randomString(10));
        Allure.step("Group modification", step -> {
            app.groups().modifyGroup(oldGroups.get(index), testData);
        });
        var newGroups = app.hbm().getGroupList();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.set(index, testData.withId(oldGroups.get(index).id()));
        Allure.step("Validating result", step -> {
            Assertions.assertEquals(Set.copyOf(newGroups), Set.copyOf(expectedList));
        });
    }
}
