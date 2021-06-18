package in.aorder.qr.util.common;

import in.aorder.qr.entity.QrCode;

public class EntityBuilder {

    /**
     *t Utility method to build QrCode instance with the provided params.
     *
     * @param qrCode entity
     * @param metadata string containing metadata of QR code
     * @param imageUrl url of the QrCode image
     */
    public static void build(QrCode qrCode, String metadata, String imageUrl) {
        qrCode.setMetadata(metadata);
        qrCode.setImageUrl(imageUrl);
    }

}
