package com.adur.transkal.frw.whatsapp.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtil {
    private static Properties properties = new Properties();

    static {
        try (InputStream input = ConfigUtil.class.getClassLoader().getResourceAsStream("WhatsappApi.properties")) {
            if (input != null) {
                properties.load(input);
            } else {
                throw new IOException("No se pudo encontrar el archivo WhatsappApi.properties");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}