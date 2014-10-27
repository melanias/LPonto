package br.com.lponto.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.lponto.validation.SetorValidation;

/**
 *
 * @author Phelipe Melanias
 */
@Entity
@SetorValidation
@Table(name="setor")
public class Setor implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="id_setor_seq", sequenceName="id_setor_seq")
    @GeneratedValue(generator="id_setor_seq", strategy=GenerationType.AUTO)
    private Integer id;

    @NotNull
    @Size(min=2, max=150)
    @Column(length=150, nullable=false, unique=true)
    private String nome;

    @NotNull
    @Size(min=2, max=50)
    @Column(length=50, nullable=false, unique=true)
    private String sigla;

    @Column(length=100)
    private String email;

    @Column(length=14)
    private String telefone;

    @Column(length=14)
    private String fax;

    @OneToMany(mappedBy="setor")
    private List<Funcionario> funcionarios;

    //getters e setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) {
        if (nome == null) {
            this.nome = nome;
        } else {
            this.nome = nome.trim();
        }
    }

    public String getSigla() { return sigla; }
    public void setSigla(String sigla) {
        if (sigla == null) {
            this.sigla = sigla;
        } else {
            this.sigla = sigla.trim().toUpperCase();
        }
    }

    public String getEmail() { return email; }
    public void setEmail(String email) {
        if (email == null) {
            this.email = email;
        } else {
            this.email = email.trim();
        }
    }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getFax() { return fax; }
    public void setFax(String fax) { this.fax = fax; }

    public List<Funcionario> getFuncionarios() { return funcionarios; }
    public void setFuncionarios(List<Funcionario> funcionarios) { this.funcionarios = funcionarios; }
}