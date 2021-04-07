package utils;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

public class ReadArchives {

    public static void unzipWithPassword(String ZipPath, String unzipPath, String password) throws ZipException {
        ZipFile zipFile = new ZipFile(ZipPath);
        if (zipFile.isEncrypted()) {
            zipFile.setPassword(password);
        }
        zipFile.extractAll(unzipPath);
    }
}

