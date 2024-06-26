package dev.dnnr.api.domain.user;

public enum UserRole {
    ADMIN("admin"),
    USER("user");

    private final String roles;

    UserRole(String roles){
        this.roles = roles;
    }

    public String getRole() {
        return roles;
    }
}
