package in.aorder.qr.util.fileupload;

import java.io.File;

public interface FileUploadProvider {

    /**
     * Uploads file to a file server and returns the download url.
     *
     * @param content content of the file to be uploaded
     * @param fileName name of file after upload
     * @return download url
     */
    String upload(byte[] content, String fileName);

}
