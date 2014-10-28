package br.com.lponto.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import br.com.lponto.enumeration.Role;
import br.com.lponto.enumeration.Status;
import br.com.lponto.validation.FuncionarioValidation;

/**
 *
 * @author Phelipe Melanias
 */
@Entity
@FuncionarioValidation
@Table(name="funcionario")
public class Funcionario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="id_funcionario_seq", sequenceName="id_funcionario_seq")
    @GeneratedValue(generator="id_funcionario_seq", strategy=GenerationType.AUTO)
    private Integer id;

    @Temporal(TemporalType.DATE)
    @Column(name="dt_cadastro", nullable=false, updatable=false)
    private Date data;

    @CPF
    @NotNull
    @Column(length=14, unique=true, nullable=false)
    private String cpf;

    @NotNull
    @Size(min=2, max=200)
    @Column(length=200, unique=true, nullable=false)
    private String nome;

    @Column(length=128, nullable=false)
    private String senha;

    @Transient
    private String checkPasswd; //É passwd mesmo ;)

    @Enumerated(EnumType.ORDINAL)
    @Column(columnDefinition="smallint", nullable=false)
    private Role perfil = Role.ADMINISTRADOR;

    @Enumerated(EnumType.ORDINAL)
    @Column(columnDefinition="smallint", nullable=false)
    private Status status = Status.ATIVO;

    @ManyToOne
    @JoinColumn(name="id_setor", referencedColumnName="id")
    private Setor setor;

    @OneToMany(mappedBy="funcionario")
    private List<Ponto> registrosDePonto;

    //getters e setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Date getData() { return data; }
    public void setData(Date data) { this.data = data; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getNome() { return nome; }
    public void setNome(String nome) {
        if (nome == null) {
            this.nome = nome;
        } else {
            this.nome = nome.trim();
        }
    }

    public String getSenha() { return senha; }
    public void setSenha(String senha) {
        if (senha == null) {
            this.senha = senha;
        } else {
            this.senha = senha.trim();
        }
    }

    public String getCheckPasswd() { return checkPasswd; }
    public void setCheckPasswd(String checkPasswd) {
        if (checkPasswd == null) {
            this.checkPasswd = checkPasswd;
        } else {
            this.checkPasswd = checkPasswd.trim();
        }
    }

    public Role getPerfil() { return perfil; }
    public void setPerfil(Role perfil) { this.perfil = perfil; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public Setor getSetor() { return setor; }
    public void setSetor(Setor setor) { this.setor = setor; }

    public String getFirstAndLastName() {
        String[] s = nome.split(" ");
        return ((s.length > 2) ? s[0] + " " + s[s.length - 1] : nome);
    }

    public List<Ponto> getRegistrosDePonto() { return registrosDePonto; }
    public void setRegistrosDePonto(List<Ponto> registrosDePonto) { this.registrosDePonto = registrosDePonto; }
}