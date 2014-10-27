package br.com.lponto.enumeration;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Phelipe Melanias
 */
public enum Status implements Serializable {
    INATIVO("Inativo"), ATIVO("Ativo");

    private final String status;

    private Status(String status) {
        this.status = status;
    }

    //getters
    public String getStatus() { return status; }
    public static List<Status> getAll() { return Arrays.asList(values()); }
}