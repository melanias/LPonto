package br.com.lponto.repository;

/**
 *
 * @author Phelipe Melanias
 */
public class RepositoryException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public RepositoryException(String msg) {
        super(msg);
    }

    public RepositoryException(Throwable e) {
        super(e);
    }

    public RepositoryException(String msg, Throwable e) {
        super(msg, e);
    }
}