package com.demo.logging;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.demo.pojos.Form;

//TODO: refactor this and configure console output also + files with date title..
public class LoggerConfig {

	static {
		final InputStream inputStream = Form.class.getResourceAsStream("/logging.properties");
		try {
			LogManager.getLogManager().readConfiguration(inputStream);
		    Logger.getAnonymousLogger().info("logging test");
		} catch (final IOException e) {
			Logger.getAnonymousLogger().severe("Could not load default logging.properties file");
			Logger.getAnonymousLogger().severe(e.getMessage());
		}
	}

}
