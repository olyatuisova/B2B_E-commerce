import java.util.ArrayList;
import java.util.List;

public class Admin extends User {
    private String department;
    private List<String> permissions;

    public Admin(int id, String name, String email, String password, String department) {
        super(id, name, email, password);
        this.department = department;
        this.permissions = new ArrayList<>();
    }

    @Override
    public String getRole() {
        return "ADMIN";
    }

    @Override
    public void showDashboard() {
        System.out.println("Admin's panel: " + getName());
        System.out.println("Department: " + department);
        System.out.println("Permissions: " + permissions);
    }

    public void addPermission(String perm) {
        permissions.add(perm);
        System.out.println("Permission added: " + perm);
    }

    public void addProduct(Product product) {
        System.out.println("Product was added: " + product.getName());
    }
}