package br.com.lponto.bean;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.lponto.enumeration.Register;

/**
 *
 * @author Phelipe Melanias
 */
@Entity
@Table(name="ponto")
public class Ponto extends Arquivo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="id_ponto_seq", sequenceName="id_ponto_seq")
    @GeneratedValue(generator="id_ponto_seq", strategy=GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(nullable=false, updatable=false)
    private Date toDate;

    @Temporal(TemporalType.TIME)
    @Column(nullable=false, updatable=false)
    private Date toTime;

    @Enumerated(EnumType.ORDINAL)
    @Column(columnDefinition="smallint", nullable=false)
    private Register tipo;

    @ManyToOne
    @JoinColumn(name="id_funcionario", referencedColumnName="id", nullable=false, updatable=false)
    private Funcionario funcionario;

    //getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Date getToDate() { return toDate; }
    public void setToDate(Date toDate) { this.toDate = toDate; }

    public Date getToTime() { return toTime; }
    public void setToTime(Date toTime) { this.toTime = toTime; }

    public Register getTipo() { return tipo; }
    public void setTipo(Register tipo) { this.tipo = tipo; }

    public Funcionario getFuncionario() { return funcionario; }
    public void setFuncionario(Funcionario funcionario) { this.funcionario = funcionario; }
}