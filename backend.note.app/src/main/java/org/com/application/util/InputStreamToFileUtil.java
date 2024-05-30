package org.com.application.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.Optional;

import com.note.web.util.constants.Constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InputStreamToFileUtil {

    public static Optional<String> inputStreamToFile(String document, InputStream inputStream)
            throws FileNotFoundException, IOException {
        String fileName = document.concat("-".concat(String.valueOf(LocalDate.now()))).concat(".csv");
        String outputPath = Constants.PATH_DIRECOTRY.concat(fileName);
        try (OutputStream outputStream = new FileOutputStream(new File(outputPath))) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
        }
        // Establecer permisos en el archivo creado
        File outputFile = new File(outputPath);
        outputFile.setReadable(true, false); // Permisos de lectura para todos
        return Optional.of(fileName);
    }
}
