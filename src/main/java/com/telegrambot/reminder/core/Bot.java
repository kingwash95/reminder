package com.telegrambot.reminder.core;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class  Bot extends TelegramLongPollingBot{
    private long chatId;
    private String inText;


    /** Метод-обработчик поступающих сообщений. @param update объект, содержащий информацию о входящем сообщений*/
    @Override
    public void onUpdateReceived(Update update) {

        //проверяем есть ли сообщение и текстовое ли оно
        if (update.hasMessage() && update.getMessage().hasText()) {
            //Извлекаем объект входящего сообщения
            Message inMessage = update.getMessage();
            //Извлекаем id чата
            chatId = update.getMessage().getChatId();
            //Извлекаем текст входящего сообщения
            inText = update.getMessage().getText();
        }
        command(inText, update);

    }

    public void command(String inText, Update update){
        if (inText.contains("/add")){
            String dataString = "r";
            String reminder = "rr";
            sendMessage("Введите дату, на которую вы хотите выставить напоминание, в следующем формате: ДД-ММ-ГГГГ");
            if (update.hasMessage() && update.getMessage().hasText()) {
                Message inMessage = update.getMessage();
                dataString = update.getMessage().getText();
            }
            sendMessage("Введите напоминание:");
            if (update.hasMessage() && update.getMessage().hasText()){
                Message inMessage = update.getMessage();
                reminder = update.getMessage().getText();
            }
            sendMessage(dataString + " " + reminder);

        }
    }
    public void sendMessage (String outText){
        try {
                //Создаем исходящее сообщение
                SendMessage outMessage = new SendMessage();
                //Указываем в какой чат будем отправлять сообщение
                //(в тот же чат, откуда пришло входящее сообщение)
                outMessage.setChatId(chatId);
                //Указываем текст сообщения
                outMessage.setText(outText);
                //Отправляем сообщение
                execute(outMessage);

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
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