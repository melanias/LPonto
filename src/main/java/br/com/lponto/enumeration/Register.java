package br.com.lponto.enumeration;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * @author Phelipe Melanias
 */
public enum Register implements Serializable {
    ENTRADA("Entrada"), SAIDA("Saída");

    private final String register;

    private Register(String register) {
        this.register = register;
    }

    //getters
    public String getRegister() { return register; }
    public static List<Register> getAll() { return Arrays.asList(values()); }
}