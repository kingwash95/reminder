package com.telegrambot.reminder.classes;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {
    private long chatId;
    @Override
    public void onUpdateReceived(Update update) {
        update.getUpdateId();

        SendMessage sendMessage = new SendMessage().setChatId(update.getMessage().getChatId());
        chatId = update.getMessage().getChatId();
        sendMessage.setText((input(update.getMessage().getText())));

            try {
                execute(sendMessage);
            } catch (TelegramApiException e){
                e.printStackTrace();
            }

    }
    public String input (String msg){
        if(msg.contains("hi"))  return "hiiii";
        return msg;
    }
    @Override
    public String getBotUsername() {
        return "@Rem1nd3rBot";
    }

    @Override
    public String getBotToken() {
        return "910589597:AAGEqkKyxMmKqnLq5ycIlEZhJRfTdNnyLiI";
    }
}
