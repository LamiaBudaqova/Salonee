package com.backend134.salon.services.impls;

import com.backend134.salon.services.TelegramNotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Slf4j
@Service
public class TelegramNotificationServiceImpl implements TelegramNotificationService {

    @Value("${telegram.bot.token}")
    private String botToken;

    @Value("${telegram.chat.id}")
    private String chatId;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public void sendTelegramMessage(String phone, String message) {
        try {
            // 🔹 Telegram API URL
            String url = String.format("https://api.telegram.org/bot%s/sendMessage", botToken);

            // 🔹 JSON body hazırlayırıq
            Map<String, Object> body = Map.of(
                    "chat_id", chatId,
                    "text", message,
                    "parse_mode", "HTML" // emoji və s. dəstəklənir
            );

            // 🔹 Header-lar
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // 🔹 Request göndər
            HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);
            restTemplate.postForObject(url, request, String.class);

            log.info("✅ Telegram mesaj göndərildi (nömrə: {}): {}", phone, message);

        } catch (Exception e) {
            log.error("❌ Telegram mesaj göndərilmədi ({}): {}", phone, e.getMessage());
        }
    }
}
