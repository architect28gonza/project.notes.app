package com.note.web.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UtilFile {

    public static void removeFileTmp(File file) {
        try {
            Files.deleteIfExists(file.toPath());
        } catch (IOException e) {
            UtilLogger.error("Ocurrio un error al momento de eliminar el archivo : ", e.getMessage());
        }
    }

    public static boolean isExistsDirectory(File directory) {
        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                UtilLogger.error("El directorio no se encuentra : ".concat(directory.getPath()), null);
                return false;
            }
        }
        return true;
    }

}
