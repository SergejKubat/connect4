package com.main.connect4client.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.main.connect4client.settings.Constants;
import com.main.connect4shared.domain.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class WebService {
    public WebService() {
    }

    public List<Player> getAllPlayers() {
        try {
            // Create URL
            URL url = new URL(Constants.WEB_SERVICE_URL + "players");

            // Open connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Read response
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder responseStringBuilder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                responseStringBuilder.append(line);
            }

            reader.close();

            // Close connection
            connection.disconnect();

            // Parse JSON array response into a list of players
            ObjectMapper objectMapper = new ObjectMapper();

            return objectMapper.readValue(responseStringBuilder.toString(), new TypeReference<>() {
            });
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
