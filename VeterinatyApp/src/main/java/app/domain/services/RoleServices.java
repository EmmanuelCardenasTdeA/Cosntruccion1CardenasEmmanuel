package app.domain.services;

import app.domain.models.User;

public class RoleServices {
    
    public String getNameRole(User user) {
        if (user.getRole() == 1) {
            return "Admin";
        } else if (user.getRole() == 2) {
            return "Veterinary";
        } else {
            return "Client";
        }
    }
}
