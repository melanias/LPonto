package br.com.lponto.config;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;

import br.com.caelum.vraptor.observer.upload.DefaultMultipartConfig;

/**
 *
 * @author Phelipe Melanias
 */
@Specializes
@ApplicationScoped
public class CustomMultipartConfig extends DefaultMultipartConfig{

    @Override
    public long getSizeLimit() {
        return 30 * 1024 * 1024;
    }

    @Override
    public long getFileSizeLimit() {
        return 10 * 1024 * 1024;
    }
}