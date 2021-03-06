package br.com.lponto.business;

import br.com.caelum.vraptor.observer.download.Download;
import br.com.caelum.vraptor.observer.upload.UploadedFile;

import br.com.lponto.bean.Arquivo;

/**
 *
 * @author Phelipe Melanias
 */
public interface ArquivoBusiness {

    void showFile(Arquivo arquivo);

    boolean isUploadedFileNotNull(UploadedFile arquivo);

    Download makeUploadedFileDownloadable(Arquivo arquivo);

    byte[] convertUploadedFileToByteArray(UploadedFile arquivo);
}