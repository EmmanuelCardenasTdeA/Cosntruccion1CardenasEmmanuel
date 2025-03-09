package app.domain.models;

public enum Role {

    ADMIN("ADMIN", 0), VET("Veterinario", 1), SELLER("Vendedor", 2), CLIENT("Cliente", 3);
    
    private String roleName;
    private int roleId;

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

    public String getRoleNameByRoleId(int roleId){
        switch (roleId) {
            case 1:
                return ADMIN.getRoleName();
            case 2:
                return VET.getRoleName();
            case 3:
                return SELLER.getRoleName();
            case 4:
                return CLIENT.getRoleName();
            default:
                return null;
        }
    }
}
