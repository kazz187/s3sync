package zone.kaz.s3sync.cli;

import zone.kaz.s3sync.core.FileScanner;
import zone.kaz.s3sync.core.FileUploader;

import java.io.File;
import java.util.List;

/**
 * Created by kazz on 2014/06/25.
 */
public class Main {

    public static void main(String args[]) {
        new ConfigOption().setting(args);
        List<File> fileList = new FileScanner().scan();
        new FileUploader().upload(fileList);
    }

}
