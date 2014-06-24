package zone.kaz.s3sync.core;

import java.io.File;

/**
 * Created by kazz on 2014/06/25.
 */
public class FileUploadingTask implements Runnable {

	private final File file;

	public FileUploadingTask(File file) {
		this.file = file;
	}

	@Override
	public void run() {
		// TODO: send to s3 here
		System.out.println(file.getAbsolutePath());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
