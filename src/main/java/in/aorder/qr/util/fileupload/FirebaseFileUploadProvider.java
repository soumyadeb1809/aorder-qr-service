package in.aorder.qr.util.fileupload;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;

public class FirebaseFileUploadProvider implements FileUploadProvider {

    private static final Logger LOG = LogManager.getLogger(FirebaseFileUploadProvider.class);

    @Value("${firebase.storage.bucket}")
    private static String BUCKET;

    @Value("${firebase.storage.url.template}")
    private static String DOWNLOAD_URL;

    @Value("${firebase.storage.file.prefix}")
    private static String FILE_PREFIX;

    @Override
    public String upload(byte[] content, String fileName) {
        try {
            fileName = FILE_PREFIX + fileName;
            BlobId blobId = BlobId.of(BUCKET, fileName);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();

            Storage storage = getStorageClient();
            storage.create(blobInfo, content);

            String downloadUrl = String.format(DOWNLOAD_URL, URLEncoder.encode(fileName, "utf-8"));
            LOG.info("Uploaded " + fileName + " to: " + downloadUrl);
        }
        catch (IOException e) {
            LOG.error("Failed to upload: " + fileName, e);
        }

        return null;
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
