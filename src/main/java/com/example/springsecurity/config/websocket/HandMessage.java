package com.example.springsecurity.config.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

@Slf4j
public class HandMessage extends TextWebSocketHandler {
    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) {
        try {
            log.info("Client message : {}" + message.getPayload());
            session.sendMessage(new TextMessage("ok  from server"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
