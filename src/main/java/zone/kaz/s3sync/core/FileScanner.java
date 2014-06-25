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
        List<File> fileList;
        if (sourceDir.isDirectory()) {
            fileList = scanDir(sourceDir);
        } else {
            fileList = new ArrayList<File>();
            fileList.add(sourceDir);
        }
        return fileList;
    }

    private List<File> scanDir(File dir) {
        if (dir == null) {
            return new ArrayList<File>();
        }
        File files[] = dir.listFiles();
        if (files == null) {
            return new ArrayList<File>();
        }
        List<File> fileList = new ArrayList<File>();
        for (File file : files) {
            if (file.isDirectory()) {
                fileList.addAll(scanDir(file));
            } else {
                fileList.add(file);
            }
        }
        return fileList;
    }

}
