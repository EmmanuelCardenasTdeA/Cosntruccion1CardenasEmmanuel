package app.domain.models;

public enum Role {

    ADMIN("admin", 1), VET("veterianrian", 2), SELLER("seller", 3);

    public String roleName;
    public int roleId;

    private Role(String roleName, int roleId) {
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
