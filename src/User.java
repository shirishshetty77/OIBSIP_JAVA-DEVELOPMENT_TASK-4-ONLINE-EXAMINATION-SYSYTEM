public class User {
    private String username;
    private String password;
    private String fullName;

    // NEW fields for results
    private Integer lastScore;
    private Integer lastTotal;

    public User(String username, String password, String fullName) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getFullName() { return fullName; }

    public void setPassword(String password) { this.password = password; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    // Results
    public void setLastResult(int score, int total) {
        this.lastScore = score;
        this.lastTotal = total;
    }
    public Integer getLastScore() { return lastScore; }
    public Integer getLastTotal() { return lastTotal; }
}