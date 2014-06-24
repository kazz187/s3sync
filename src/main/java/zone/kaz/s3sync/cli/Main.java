package zone.kaz.s3sync.cli;

import zone.kaz.s3sync.core.Config;

/**
 * Created by kazz on 2014/06/25.
 */
public class Main {

	public static void main(String args[]) {
		new ConfigOption().setting(args);
	}

}
