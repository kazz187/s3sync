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
            List<File> dirList = new ArrayList<File>();
            dirList.add(sourceDir);
            return scanDir(fileList, dirList);
        }
        fileList.add(sourceDir);
        return fileList;
    }

    private List<File> scanDir(List<File> fileList, List<File> dirList) {
        if (dirList.isEmpty()) {
            return fileList;
        }
        List<File> dirs = new ArrayList<File>();
        for (File dir : dirList) {
            for (File file : dir.listFiles()) {
                if (file.isDirectory()) {
                    dirs.add(file);
                } else {
                    fileList.add(file);
                }
            }
        }
        return scanDir(fileList, dirs);
    }

}
