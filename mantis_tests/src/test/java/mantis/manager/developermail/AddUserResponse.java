package mantis.manager.developermail;

import mantis.model.DeveloperMailUser;

public record AddUserResponse(
        boolean success,
        Object errors,
        DeveloperMailUser result) {
}
