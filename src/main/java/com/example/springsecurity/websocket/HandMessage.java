package com.example.springsecurity.websocket;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Slf4j
public class HandMessage extends TextWebSocketHandler {
    private final String URL = "https://api.openai.com/v1/chat/completions";
    private final String API_KEY = "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";

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        log.info("Message from client: {}", message.getPayload());
        session.sendMessage(new TextMessage("Hello client !"));
    }

    private String getMessageResponse(String messageRequest) throws UnsupportedEncodingException {
        try {
            Gson gson = new Gson();
            HttpClient httpClient = HttpClients.createDefault();
            HttpPost request = new HttpPost(URL);
            request.addHeader("Authorization", "Bearer " + API_KEY);
            StringEntity params = new StringEntity(messageRequest);
            request.setEntity(params);
            HttpResponse httpResponse = httpClient.execute(request);
            HttpEntity entity = httpResponse.getEntity();
            return EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
