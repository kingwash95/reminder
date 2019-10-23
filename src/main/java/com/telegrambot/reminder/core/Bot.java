package com.telegrambot.reminder.core;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class  Bot extends TelegramLongPollingBot{
    private long chatId;
    private String inText;
    private int key = 0;
    private String stringData;
    private String stringReminder;


    /** Метод-обработчик поступающих сообщений. @param update объект, содержащий информацию о входящем сообщений*/
    @Override
    public void onUpdateReceived(Update update) {

        //проверяем есть ли сообщение и текстовое ли оно
        if (!update.hasMessage() || !update.getMessage().hasText())
            return;
        //Извлекаем объект входящего сообщения
        Message inMessage = update.getMessage();
        //Извлекаем id чата
        chatId = update.getMessage().getChatId();
        //Извлекаем текст входящего сообщения
        inText = update.getMessage().getText();
        if (key == 1){
            toDataBase(inText);
        }
        commandDate(inText);




    }

    public void commandDate(String inText){
        if (inText.contains("/add")){
            sendMessage("Введите дату в формате ДД-ММ-ГГГГ и текст напоминания по следующему примеру:'22-08-2019, купить хлеб");
            key = 1;
        }
    }
    public void toDataBase(String inText){
        key = 0;
        String [] parts = inText.split(",");
        stringData = parts[0];
        stringReminder = parts[1];
        sendMessage(stringData + " " + stringReminder);
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