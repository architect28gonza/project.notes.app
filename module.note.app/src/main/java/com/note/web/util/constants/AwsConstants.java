package com.note.web.util.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AwsConstants {

    /* Nombre de la cola de aws - Test */
    public static final String NAME_QUEUE = "cola-note-dev";
    
    /* Nombre del bucket para subir los archivos - Test */
    public static final String NAME_BUCKET_S3 = "bucket-aws-test-note";   
}
