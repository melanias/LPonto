package br.com.lponto.bean;

import java.io.Serializable;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Phelipe Melanias
 */
@MappedSuperclass
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Arquivo implements Serializable {
    private static final long serialVersionUID = 1L;

    byte[] file;

    private String fileName;

    private String mimeType;

    //getters e setters
    public byte[] getFile() { return file; }
    public void setFile(byte[] file) { this.file = file; }

    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }

    public String getMimeType() { return mimeType; }
    public void setMimeType(String mimeType) { this.mimeType = mimeType; }

    public String getEncodeBase64() {
        return new StringBuffer("data:")
                        .append(getMimeType())
                        .append(";base64,")
                        .append(Base64.encodeBase64String(file)).toString();
    }

    public String getFileExtension() {
        int i = fileName.lastIndexOf(".");
        return (i == -1) ? null : fileName.substring(i);
    }
}