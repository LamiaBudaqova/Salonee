package com.backend134.salon.services;

public interface TelegramNotificationService {
    void sendTelegramMessage(String phone, String message);
}
