package com.epam.services.properties;

import com.epam.exceptions.PropertiesNotLoadedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesReader.class);
    private static final String PROPERTY_FILE_PATH = "src/main/resources/";

    public static String getProperty(String propertyFileName, String propertyName) {
        String propertyFilePath = String.format("%s%s", PROPERTY_FILE_PATH, propertyFileName);

        Properties properties = new Properties();
        try (InputStream input = new FileInputStream(propertyFilePath)) {
            properties.load(input);
            return properties.getProperty(propertyName);
        } catch (IOException e) {
            LOGGER.warn(String.format("Unable to read property %s for from: %s", propertyName, propertyFileName));
            throw new PropertiesNotLoadedException("Unable to open stream for resource ".concat(propertyFileName), e);
        }
    }

    public static String readSecret(String secretName) {
        String secretValue = System.getenv(secretName);
        if (secretValue == null) {
            throw new RuntimeException("Secret '" + secretName + "' not found");
        }
        return secretValue;
    }
}
