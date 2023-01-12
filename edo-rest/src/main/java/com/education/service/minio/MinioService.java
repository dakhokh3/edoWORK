package com.education.service.minio;

import java.io.IOException;

/**
 * Interface of MinioService for manipulating the request to another RestController from edo-file-Storage
 */
public interface MinioService {

    /**
     * Method check connection to server MinIo with a parameters of admin.
     */
    void checkConnection();

    /**
     * Method upload the file to server MinIo.
     * Variable "objectName" means name of object, uploadibg to the bucket of minio server.
     */
    void uploadOneFile(String objectName) throws IOException;

    /**
     * Method download the file from server MinIo.
     * Variable "objectName" means name of object,
     * downloading from the bucket of minio server
     * to target folder.
     */
    void downloadOneFile(String objectName);
}