package com.arthur.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    private final Properties properties = new Properties();

    public Config() {
        try (InputStream input = getClass()
                .getClassLoader()
                .getResourceAsStream("config.properties")) {

            if (input == null) {
                throw new RuntimeException("Arquivo config.properties não encontrado!");
            }

            properties.load(input);

        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar configurações: " + e.getMessage());
        }
    }

    public String getPhone() {
        return properties.getProperty("whatsapp.phone");
    }

    public String getApiKey() {
        return properties.getProperty("whatsapp.apikey");
    }
}