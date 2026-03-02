public abstract class User {

    private int id;
    protected String name;
    private String email;
    public boolean isActive;

    public User(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email.contains("@")) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Incorrect email");
        }
    }

    public abstract String getRole();

    public abstract void showDashboard();
}
