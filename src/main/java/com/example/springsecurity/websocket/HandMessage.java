package com.example.springsecurity.websocket;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
public class HandMessage extends TextWebSocketHandler {
    private final String url = "https://api.openai.com/v1/chat/completions";

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        log.info("Message from client: {}", message.getPayload());
        session.sendMessage(new TextMessage("Hello client !"));
    }

    private String getMessageResponse(String messageRequest) {
        Gson gson = new Gson();
        return null;
    }
}
