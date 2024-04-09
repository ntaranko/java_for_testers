package mantis.manager.developermail;

public record GetMessageResponse(
        boolean success,
        Object errors,
        String result) {
}
