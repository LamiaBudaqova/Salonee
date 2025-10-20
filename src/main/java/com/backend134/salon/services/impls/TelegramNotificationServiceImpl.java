package com.backend134.salon.services.impls;

import com.backend134.salon.services.TelegramNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TelegramNotificationServiceImpl implements TelegramNotificationService {

    @Value("${telegram.bot.token}")
    private String botToken;

    @Value("${telegram.chat.id}")
    private String chatId;

    @Override
    public void sendMessage(String message) {
        try {
            // ✅ 1. Telegram API endpoint
            String url = "https://api.telegram.org/bot" + botToken + "/sendMessage";

            // ✅ 2. Mesajın bədəni (JSON kimi göndəririk)
            Map<String, String> body = new HashMap<>();
            body.put("chat_id", chatId);
            body.put("text", message);

            // ✅ 3. HTTP POST ilə göndəririk (artıq encode etmirik!)
            new RestTemplate().postForObject(url, body, String.class);

            System.out.println("✅ Telegram mesajı göndərildi: " + message);
        } catch (Exception e) {
            System.err.println("❌ Telegram mesajı göndərilmədi: " + e.getMessage());
        }
    }
}
