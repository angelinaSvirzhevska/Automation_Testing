package api;

public class AuthorDTO {
    //@SerializedName() анотацию можно не использовать,
    // если название поля точно совпадает с названием поля в ответе
    String username;
    String avatar;

    public AuthorDTO(String username) {
        this.username = username;
    }

    public AuthorDTO(){
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "AuthorDTO{" +
                "username='" + username + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
