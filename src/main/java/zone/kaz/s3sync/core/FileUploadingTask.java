package zone.kaz.s3sync.core;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import java.io.File;

/**
 * Created by kazz on 2014/06/25.
 */
public class FileUploadingTask implements Runnable {

    private final File rootFile;
    private final File file;
    private final String relativePath;
    private String bucketName = Config.bucket;
    private String bucketPath = Config.bucketPath;
    private String dirName = Config.dirName;
    private final static AmazonS3Client amazonS3Client = new AmazonS3Client(
            new BasicAWSCredentials(Config.accessKey, Config.secretKey));

    public FileUploadingTask(File rootFile, File file) {
        this.rootFile = rootFile;
        this.file = file;
        this.relativePath = getRelativePath(rootFile, file);
    }

    @Override
    public void run() {
        System.out.println(relativePath);
        String s3FilePath = getS3FilePath();
        PutObjectRequest request = new PutObjectRequest(bucketName, s3FilePath, file);

        ObjectMetadata metadata = new ObjectMetadata();
        for (String key : Config.header.keySet()) {
            metadata.setHeader(key, Config.header.get(key));
            request.setMetadata(metadata);
        }

        request.withCannedAcl(Config.aclPublic ? CannedAccessControlList.PublicRead : CannedAccessControlList.Private);

        boolean retry;
        int retryCount = 10;
        do {
            retry = false;
            try {
                amazonS3Client.putObject(request);
            } catch (Exception e) {
                System.out.println("[RETRY] Failed to upload: " + relativePath + " " + e.getMessage());
                retry = retryCount-- > 0;
            }
        } while (retry);
    }

    private String getS3FilePath() {
        String s3FilePath = dirName + "/" + relativePath;
        if (!bucketPath.equals("")) {
            s3FilePath = bucketPath + "/" + s3FilePath;
        }
        return s3FilePath;
    }

    private String getRelativePath(File rootFile, File file) {
        String rootFilePath = rootFile.getAbsolutePath();
        String filePath = file.getAbsolutePath();
        return filePath.replaceFirst(rootFilePath + "/", "");
    }

}
