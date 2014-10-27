package br.com.lponto.enumeration;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * @author Phelipe Melanias
 */
public enum Role implements Serializable {
    GESTOR("Gestor"), NORMAL("Normal"), ADMINISTRADOR("Administrador");

    private final String role;

    private Role(String role) {
        this.role = role;
    }

    //getters
    public String getRole() { return role; }
    public static List<Role> getAll() { return Arrays.asList(values()); }
}