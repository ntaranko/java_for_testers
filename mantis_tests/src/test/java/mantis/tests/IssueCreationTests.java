package mantis.tests;

import mantis.common.CommonFunctions;
import mantis.model.IssueData;
import org.junit.jupiter.api.Test;

public class IssueCreationTests extends TestBase{

    @Test
    void canCreateIssue(){
        app.rest().createIssue(new IssueData()
                .withSummary(CommonFunctions.randomString(10))
                .withDescription(CommonFunctions.randomString(50))
                .withProject(1L));
    }
}
