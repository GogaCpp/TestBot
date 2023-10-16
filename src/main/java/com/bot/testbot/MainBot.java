package com.bot.testbot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;


import java.util.Arrays;

@Component

public class MainBot extends TelegramLongPollingBot{
    @Value("${telegram.bot.name}")
    private String botName;
    @Value("${telegram.bot.token}")
    private String botToken;

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
    @Override
    public void onUpdateReceived(Update update) {
            if (update.hasMessage() && update.getMessage().hasText()) {
                String messageText = update.getMessage().getText();
                long chatId = update.getMessage().getChatId();

            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);
            sendMessage.setText("Выберите действие:");
            ReplyKeyboardMarkup keyboardMarkup = createKeyboard();

            sendMessage.setReplyMarkup(keyboardMarkup);

            try {
                execute(sendMessage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


        private ReplyKeyboardMarkup createKeyboard() {
            ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
            keyboardMarkup.setResizeKeyboard(true);
            keyboardMarkup.setOneTimeKeyboard(true);



            KeyboardRow row1 = new KeyboardRow();
            row1.add(new KeyboardButton("Кнопка 1"));

            KeyboardRow row2 = new KeyboardRow();
            row2.add(new KeyboardButton("Кнопка 2"));

            KeyboardRow row3 = new KeyboardRow();
            row3.add(new KeyboardButton("Кнопка 3"));

            KeyboardRow row4 = new KeyboardRow();
            row4.add(new KeyboardButton("Кнопка 4"));

            keyboardMarkup.setKeyboard(Arrays.asList(row1, row2,row3,row4));

            return keyboardMarkup;
        }
    }



