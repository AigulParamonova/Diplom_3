package serializer;

public class UserAuthGenerator {
    private String email;
    private String password;

    public UserAuthGenerator(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static UserAuthGenerator from(User user){
        return new UserAuthGenerator(user.getEmail(), user.getPassword());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
