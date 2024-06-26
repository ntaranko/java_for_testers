package tests.groups;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.CommonFunctions;
import io.qameta.allure.Allure;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import tests.TestBase;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class GroupCreationTest extends TestBase {

    public static List<GroupData> groupProvider() {
        var result = new ArrayList<GroupData>();
       /* for (var name : List.of("", "group name")) {
            for (var header : List.of("", "group header")) {
                for (var footer : List.of("", "group footer")) {
                    result.add(new GroupData().withName(name).withHeader(header).withFooter(footer));
                }
            }
        }*/
        for (int i = 0; i < 3; i++) {
            result.add(new GroupData().withName(CommonFunctions.randomString(i * 10)).withHeader(CommonFunctions.randomString(i * 10)).withFooter(CommonFunctions.randomString(i * 10)));
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("groupProvider")
    public void canCreateMultipleGroup(GroupData group) {
        var oldGroups = app.groups().getList();
        Allure.step("Group creation", step -> {
            app.groups().createGroup(group);
        });
        var newGroups = app.groups().getList();
        Comparator<GroupData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareById);
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.add(group.withId(newGroups.get(newGroups.size() - 1).id()).withHeader("").withFooter(""));
        expectedList.sort(compareById);
        Allure.step("Validating result", step -> {
            Assertions.assertEquals(newGroups, expectedList);
        });
    }

    public static List<GroupData> jsonGroupProvider() throws IOException {
        var result = new ArrayList<GroupData>();
        /*for (var name : List.of("", "group name")) {
            for (var header : List.of("", "group header")) {
                for (var footer : List.of("", "group footer")) {
                    result.add(new GroupData().withName(name).withHeader(header).withFooter(footer));
                }
            }
        }*/
        ObjectMapper mapper = new ObjectMapper();
        //====================
        // 1st way
        /*
        var value = mapper.readValue(new File("groups.json"), new TypeReference<List<GroupData>>(){});
        */

        // 2d way
        var json = Files.readString(Paths.get("groups.json"));
        var value = mapper.readValue(json, new TypeReference<List<GroupData>>() {
        });

        // 3d way
       /* var json = "";
        try (var reader = new FileReader("groups.json");
             var bReader = new BufferedReader(reader)) {
            var line = bReader.readLine();
            while (line != null) {
                json = json + line;
                line = bReader.readLine();
            }
        }
        var value = mapper.readValue(json, new TypeReference<List<GroupData>>() {
        });*/
        //=======================

        result.addAll(value);
        return result;
    }

    @ParameterizedTest
    @MethodSource("jsonGroupProvider")
    public void canCreateMultipleGroupFromJson(GroupData group) {
        var oldGroups = app.groups().getList();
        Allure.step("Group creation", step -> {
            app.groups().createGroup(group);
        });
        var newGroups = app.groups().getList();
        Comparator<GroupData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareById);

        var expectedList = new ArrayList<>(oldGroups);
        expectedList.add(group.withId(newGroups.get(newGroups.size() - 1).id()).withHeader("").withFooter(""));
        expectedList.sort(compareById);
        Allure.step("Validating result", step -> {
            Assertions.assertEquals(newGroups, expectedList);
        });
    }

    public static List<GroupData> negativeGroupProvider() {
        var result = new ArrayList<GroupData>(List.of(new GroupData("", "group name'", "", "")));
        return result;
    }

    @ParameterizedTest
    @MethodSource("negativeGroupProvider")
    public void canNotCreateGroup(GroupData group) {
        var oldGroups = app.groups().getList();
        Allure.step("Group creation", step -> {
            app.groups().createGroup(group);
        });
        var newGroups = app.groups().getList();
        Allure.step("Validating result", step -> {
            Assertions.assertEquals(oldGroups, newGroups);
        });
    }

    public static List<GroupData> singleGroupProvider() {
        return List.of(new GroupData()
                .withName(CommonFunctions.randomString(10))
                .withHeader(CommonFunctions.randomString(15))
                .withFooter(CommonFunctions.randomString(20)));
    }

    @ParameterizedTest
    @MethodSource("singleGroupProvider")
    public void canCreateGroupJdbc(GroupData group) {
        var oldGroups = app.jdbc().getGroupList();
        Allure.step("Group creation", step -> {
            app.groups().createGroup(group);
        });
        var newGroups = app.jdbc().getGroupList();
        Comparator<GroupData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareById);
        var maxId = newGroups.get(newGroups.size() - 1).id();

        var expectedList = new ArrayList<>(oldGroups);
        expectedList.add(group.withId(maxId));
        expectedList.sort(compareById);
        Allure.step("Validating result", step -> {
            Assertions.assertEquals(newGroups, expectedList);
        });
    }

    public static Stream<GroupData> groupsProviderStream() {
        Supplier<GroupData> randomGroup = () -> new GroupData()
                .withName(CommonFunctions.randomString(10))
                .withHeader(CommonFunctions.randomString(15))
                .withFooter(CommonFunctions.randomString(20));
        return Stream.generate(randomGroup).limit(1);
    }

    @ParameterizedTest
    @MethodSource("groupsProviderStream")
    public void canCreateGroupHibernate(GroupData group) {
        var oldGroups = app.hbm().getGroupList();
        Allure.step("Group creation", step -> {
            app.groups().createGroup(group);
        });
        var newGroups = app.hbm().getGroupList();
        var extraGroups = newGroups.stream().filter(
                g -> !oldGroups.contains(g)).toList();
        var newId = extraGroups.get(0).id();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.add(group.withId(newId));
        Allure.step("Validating result", step -> {
            Assertions.assertEquals(Set.copyOf(newGroups), Set.copyOf(expectedList));
        });
    }
}