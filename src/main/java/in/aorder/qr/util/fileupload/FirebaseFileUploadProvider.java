package in.aorder.qr.util.fileupload;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.net.URLEncoder;

import static in.aorder.qr.constant.PropertyKey.Firebase;

public class FirebaseFileUploadProvider implements FileUploadProvider {

    private static final Logger LOG = LogManager.getLogger(FirebaseFileUploadProvider.class);

    @Value(Firebase.STORAGE_BUCKET)
    private String bucket;

    @Value(Firebase.DOWNLOAD_URL_TEMPLATE)
    private String downloadUrlTemplate;

    @Value(Firebase.FILE_PREFIX)
    private String filePrefix;

    @Override
    public String upload(byte[] content, String fileName) {
        String downloadUrl = null;

        try {
            fileName = filePrefix + fileName;
            BlobId blobId = BlobId.of(bucket, fileName);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();

            Storage storage = getStorageClient();
            storage.create(blobInfo, content);

            downloadUrl = String.format(downloadUrlTemplate, URLEncoder.encode(fileName, "utf-8"));
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
