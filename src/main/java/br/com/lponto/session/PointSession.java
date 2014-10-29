package br.com.lponto.session;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.com.lponto.bean.Ponto;

/**
 *
 * @author Phelipe Melanias
 */
@SessionScoped
@Named("pointSession")
public class PointSession implements Serializable {
    private static final long serialVersionUID = 1L;

    private Ponto ponto;

    //getter e setter
    public Ponto getPonto() { return ponto; }
    public void setPonto(Ponto ponto) { this.ponto = ponto; }
}