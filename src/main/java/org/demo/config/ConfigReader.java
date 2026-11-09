package org.demo.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

/**
 * Loads framework settings from config.properties once, then exposes typed getters.
 * Keeping config in one place avoids hard-coded URLs/timeouts scattered across tests.
 */
public final class ConfigReader {

    private static final Logger LOG = LogManager.getLogger(ConfigReader.class);
    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream stream = ConfigReader.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (stream == null) {
                throw new IllegalStateException("config.properties not found on classpath");
            }
            PROPERTIES.load(stream);
            LOG.info("Loaded config.properties");
        } catch (IOException e) {
            throw new ExceptionInInitializerError("Failed to load config.properties: " + e.getMessage());
        }
    }

    private ConfigReader() {
    }

    public static String get(String key) {
        String value = PROPERTIES.getProperty(key);
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Missing config key: " + key);
        }
        return value.trim();
    }

    public static String getBaseUrl() {
        return get("baseUrl");
    }

    public static String getBrowser() {
        return get("browser").toLowerCase();
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(get("headless"));
    }

    public static boolean shouldMaximize() {
        return Boolean.parseBoolean(get("maximize"));
    }

    public static int getImplicitWait() {
        return Integer.parseInt(get("implicitWait"));
    }

    public static int getExplicitWait() {
        return Integer.parseInt(get("explicitWait"));
    }

    public static int getPageLoadTimeout() {
        return Integer.parseInt(get("pageLoadTimeout"));
    }

    public static String getOrDefault(String key, String defaultValue) {
        String value = PROPERTIES.getProperty(key);
        return (value == null || value.isBlank()) ? Objects.requireNonNull(defaultValue) : value.trim();
    }
}
