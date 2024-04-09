package mantis.model;

public record UserData(
        String name,
        String email,
        String password) {

    public UserData() {
        this("", "", "");
    }

    public UserData withName(String name) {
        return new UserData(name, this.email, this.password);
    }

    public UserData withEmail(String email) {
        return new UserData(this.name, email, this.password);
    }

    public UserData withPassword(String password) {
        return new UserData(this.name, this.email, password);
    }
}
