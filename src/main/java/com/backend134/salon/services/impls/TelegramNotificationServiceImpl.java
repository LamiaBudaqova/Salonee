package com.backend134.salon.services.impls;

import com.backend134.salon.services.TelegramNotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Service
public class TelegramNotificationServiceImpl implements TelegramNotificationService {

    @Value("${telegram.bot.token}")
    private String botToken;

    @Value("${telegram.chat.id}")
    private String chatId; // Test üçün sabit, sonra dinamik olacaq

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public void sendTelegramMessage(String phone, String message) {
        try {
            // 🔹 Mesajı URL üçün təhlükəsiz şəkildə kodlaşdır
            String encodedMessage = UriComponentsBuilder
                    .fromPath("")
                    .queryParam("text", message)
                    .build()
                    .encode()
                    .toUriString()
                    .replace("?text=", ""); // sadəcə text hissəsini saxlayırıq

            // 🔹 Telegram API URL-i hazırlayırıq
            String url = String.format(
                    "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s",
                    botToken, chatId, encodedMessage
            );

            restTemplate.getForObject(url, String.class);
            log.info("✅ Telegram mesaj göndərildi (nömrə: {}): {}", phone, message);

        } catch (Exception e) {
            log.error("❌ Telegram mesaj göndərilmədi ({}): {}", phone, e.getMessage());
        }
    }
}
