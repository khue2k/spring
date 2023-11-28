package com.example.springsecurity.websocket;

import com.example.springsecurity.dto.Message;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class HandMessage extends TextWebSocketHandler {
    private final String URL = "https://api.openai.com/v1/chat/completions";
    private final String API_KEY = "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";

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        session.sendMessage(new TextMessage(getMessageResponse(message.getPayload())));
    }

    private String getMessageResponse(String messageRequest) {
        try {
            Gson gson = new Gson();
            JSONObject jsonObjectRequest = new JSONObject();
            jsonObjectRequest.put("model", "gpt-3.5-turbo");
            List<Message> messages = Arrays.asList(new Message("user", messageRequest));
            jsonObjectRequest.put("messages", messages);
            URL url = new URL(URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + API_KEY);
            connection.setDoOutput(true);
            //set request body
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonObjectRequest.toString().getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            //read response
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine);
            }

            //parse response
            JSONObject jsonObjectResponse = new JSONObject(response.toString());
            JSONObject jsonObjectChoice = jsonObjectResponse.getJSONArray("choices").getJSONObject(0);
            JSONObject jsonObjectMessage = jsonObjectChoice.getJSONObject("message");
            Message message = gson.fromJson(jsonObjectMessage.toString(), Message.class);
            connection.disconnect();
            return message.getContent();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
