package in.aorder.qr.constant;

public class PropertyKey {

    /**
     * QR Properties
     */
    public static class Qr {
        public static final String WIDTH = "${qr.width}";
        public static final String HEIGHT = "${qr.height}";
    }


    /**
     * Firebase Properties
     */
    public static class Firebase {
        public static final String STORAGE_BUCKET = "${firebase.storage.bucket}";
        public static final String DOWNLOAD_URL_TEMPLATE = "${firebase.storage.url.template}";
        public static final String FILE_PREFIX = "${firebase.storage.file.prefix}";
        public static final String STORAGE_QR_DIRECTORY = "${firebase.storage.qr.directory}";
    }

    /**
     * App Properties
     */
    public static class App {
        public static final String BASE_PACKAGE = "${app.package.base}";
        public static final String CONTROLLER_PACKAGE = "${app.package.controller}";
        public static final String API_PATH_PREFIX = "${app.api.path-prefix}";
    }

}
