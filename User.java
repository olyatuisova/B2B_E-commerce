public abstract class User {

    private int id;
    protected String name;
    private String email;
    public boolean isActive;

    public static class UserStats {
        private static int totalUsers = 0;
        private static int totalAdmins = 0;
        private static int totalCustomers = 0;

        public static void registerUser(String role) {
            totalUsers++;
            if (role.equals("ADMIN")) totalAdmins++;
            if (role.equals("CUSTOMER")) totalCustomers++;
        }

        public static void printStats() {
            System.out.println("User Statistics: ");
            System.out.println("Total users:     " + totalUsers);
            System.out.println("Total admins:    " + totalAdmins);
            System.out.println("Total customers: " + totalCustomers);
        }
    }

    public User(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        UserStats.registerUser(getRole());
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }

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