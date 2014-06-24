package zone.kaz.s3sync.cli;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import zone.kaz.s3sync.core.Config;

import java.io.*;

/**
 * Created by kazz on 2014/06/25.
 */
public class ConfigOption {

	@Parameter(names = {"-c", "--config"}, description = "Config file name.", required = true)
	private String configFileName;

	@Parameter(names = {"-P", "--acl-public"}, description = "Store objects with ACL allowing read for anyone.")
	private boolean aclPublic = false;

	@Parameter(names = "--header", description = "Set a given HTTP header to the upload request. For instance set 'Expires' or 'Cache-Control' headers (or both) using this options if you like.")
	private String header;

	@Parameter(names = "--dir", description = "dir.", required = true)
	private String dir;

	@Parameter(names = "--bucket", description = "bucket. s3://bucket_name/path", required = true)
	private String bucket;

	@Parameter(names = "--concurrent", description = "Concurrent num")
	private int threadNum = 10;


	private static JsonFactory jsonFactory = new JsonFactory();

	private static String ACCESS_KEY_NAME = "access_key";
	private static String SECRET_KEY_NAME = "secret_key";

	public void setting(String args[]) {
		new JCommander(this, args);
		loadConfigFile();
		Config.aclPublic = aclPublic;
		Config.header = header;
		Config.dir = dir;
		Config.bucket = bucket;
		Config.threadNum = threadNum;
	}

	private void loadConfigFile() {
		File configFile = new File(configFileName);
		InputStream is = null;
		try {
			is = new FileInputStream(configFile);
		} catch (FileNotFoundException e) {
			// TODO: file exist check
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper(jsonFactory);
		JsonNode rootNode = null;
		try {
			rootNode = mapper.readTree(is);
		} catch (IOException e) {
			// TODO: invalid json check
			e.printStackTrace();
		}
		if (rootNode != null) {
			if (rootNode.has(ACCESS_KEY_NAME)) {
				Config.accessKey = rootNode.get(ACCESS_KEY_NAME).asText();
			}
			if (rootNode.has(SECRET_KEY_NAME)) {
				Config.secretKey = rootNode.get(SECRET_KEY_NAME).asText();
			}
		}
	}

}
