package zone.kaz.s3sync.core;

import java.util.HashMap;

/**
 * Created by kazz on 2014/06/25.
 */
public class Config {
	public static String accessKey;
	public static String secretKey;
	public static boolean aclPublic;
	public static HashMap<String, String> header = new HashMap<String, String>();
	public static String dir;
    public static String dirName;
	public static String bucket;
    public static String bucketPath;
	public static int threadNum;
}
