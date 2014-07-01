package zone.kaz.s3sync.core;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kazz on 2014/06/25.
 */
public class FileScanner {

    public List<File> scan() {
        File sourceDir = new File(Config.dir);
        List<File> fileList = new ArrayList<File>();
        if (sourceDir.isDirectory()) {
            scanDir(fileList, sourceDir);
        } else {
            fileList.add(sourceDir);
        }
        return fileList;
    }

    private void scanDir(List<File> fileList, File dir) {
        if (dir == null) {
            return;
        }
        File files[] = dir.listFiles();
        if (files == null) {
            return;
        }
        for (File file : files) {
            if (file.isDirectory()) {
                scanDir(fileList, file);
            } else {
                fileList.add(file);
            }
        }
    }

}
