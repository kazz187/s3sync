package zone.kaz.s3sync.core;

import java.io.File;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by kazz on 2014/06/25.
 */
public class FileUploader {

	ExecutorService executor = Executors.newFixedThreadPool(Config.threadNum);

	public void upload(List<File> fileList) {
        File rootFile = new File(Config.dir);
		for (File file : fileList) {
			executor.execute(new FileUploadingTask(rootFile, file));
		}
		executor.shutdown();
	}

}
