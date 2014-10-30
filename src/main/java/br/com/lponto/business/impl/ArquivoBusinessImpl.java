package br.com.lponto.business.impl;

import java.io.IOException;
import java.io.OutputStream;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import br.com.caelum.vraptor.observer.download.ByteArrayDownload;
import br.com.caelum.vraptor.observer.download.Download;
import br.com.caelum.vraptor.observer.upload.UploadedFile;

import br.com.lponto.bean.Arquivo;
import br.com.lponto.business.ArquivoBusiness;

/**
 *
 * @author Phelipe Melanias
 */
public class ArquivoBusinessImpl implements ArquivoBusiness {

    private HttpServletResponse response;

    /**
     * @deprecated CDI eyes only
     */
    protected ArquivoBusinessImpl() {
        this(null);
    }

    @Inject
    public ArquivoBusinessImpl(HttpServletResponse response) {
        this.response = response;
    }

    @Override
    public void showFile(Arquivo arquivo) {
        if (arquivo != null) {
            response.setContentType(arquivo.getMimeType());

            try {
                OutputStream os = response.getOutputStream();

                os.write(arquivo.getFile());

                os.flush();
                os.close();
            } catch (IOException e) {}
        }
    }

    @Override
    public boolean isUploadedFileNotNull(UploadedFile arquivo) {
        return arquivo != null;
    }

    @Override
    public Download makeUploadedFileDownloadable(Arquivo arquivo) {
        return ((arquivo == null) ? null
                                  : new ByteArrayDownload(arquivo.getFile(), arquivo.getMimeType(), arquivo.getFileName(), true));
    }

    @Override
    public byte[] convertUploadedFileToByteArray(UploadedFile arquivo) {
        if (arquivo == null) { return null; }

        try {
            return IOUtils.toByteArray(arquivo.getFile());
        } catch (IOException e) {
            return null;
        }
    }
}