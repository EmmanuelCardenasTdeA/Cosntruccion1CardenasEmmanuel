package app.domain.models;

public enum Roles {

    ADMIN("Admin", 1), VETERINARIAN("Veterinarian", 2), CLIENT("Client", 3), SELLER("Seller", 4);

    private String roleName;
    private int roleId;

    private Roles(String roleName, int roleId) {
        this.roleName = roleName;
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public int getRoleId() {
        return roleId;
    }
}