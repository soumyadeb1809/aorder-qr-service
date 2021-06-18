package in.aorder.qr.util.fileupload;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.net.URLEncoder;

public class FirebaseFileUploadProvider implements FileUploadProvider {

    private static final Logger LOG = LogManager.getLogger(FirebaseFileUploadProvider.class);

    @Value("${firebase.storage.bucket}")
    private String BUCKET;

    @Value("${firebase.storage.url.template}")
    private String DOWNLOAD_URL;

    @Value("${firebase.storage.file.prefix}")
    private String FILE_PREFIX;

    @Override
    public String upload(byte[] content, String fileName) {
        String downloadUrl = null;

        try {
            fileName = FILE_PREFIX + fileName;
            BlobId blobId = BlobId.of(BUCKET, fileName);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();

            Storage storage = getStorageClient();
            storage.create(blobInfo, content);

            downloadUrl = String.format(DOWNLOAD_URL, URLEncoder.encode(fileName, "utf-8"));
            LOG.info("Uploaded " + fileName + " to: " + downloadUrl);
        }
        catch (IOException e) {
            LOG.error("Failed to upload: " + fileName, e);
        }

        return downloadUrl;
    }

    /**
     * Helper method to create a new Firebase Storage client.
     *
     * @return Storage client
     * @throws IOException if any
     */
    private Storage getStorageClient() throws IOException {
        return StorageOptions.newBuilder()
                .setCredentials(GoogleCredentials.getApplicationDefault())
                .build().getService();
    }
}
