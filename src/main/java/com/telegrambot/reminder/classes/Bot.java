package com.telegrambot.reminder.classes;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Bot extends TelegramLongPollingBot {
    private String chat_id;
    Login login = new Login();
    @Override
    public void onUpdateReceived(Update update) {
        update.getUpdateId();

        SendMessage sendMessage = new SendMessage().setChatId(update.getMessage().getChatId());
        chat_id = String.valueOf(update.getMessage().getChatId());
        String text = update.getMessage().getText();

            try {
                sendMessage.setText(getMsg(text));
                execute(sendMessage);
                System.out.println(update.getMessage().getFrom().getUserName() + ":" + update.getMessage().getText());
            } catch (TelegramApiException e){
                e.printStackTrace();
            }

    }
    public String getMsg(String msg){
        if(msg.contains("/add")) {
            java.sql.Date date = getDate();
            String text = getText();

            login.add(chat_id, date, text);
        }
        return "OK";
    }
    public java.sql.Date getDate(){
        SendMessage sendMessage = new SendMessage().setChatId(chat_id).setText("Введите дату, на которую вы хотите выставить напоминание, в следующем формате: ДД-ММ-ГГГГ");
        try{
            execute(sendMessage);
        } catch (TelegramApiException e){
            e.printStackTrace();
        }
        Update update = null;
        update.getUpdateId();
        String dateString = update.getMessage().getText();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dat = null;
        try {
            dat = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date date = java.sql.Date.valueOf(String.valueOf(dat));
        return date;
    }
    public String getText(){
        SendMessage sendMessage1 = new SendMessage().setChatId(chat_id).setText("Введите напоминание");
        try{
            execute(sendMessage1);
        } catch (TelegramApiException e){
            e.printStackTrace();
        }
        Update update1 = null;
        update1.getUpdateId();
        String text = update1.getMessage().getText();
        return text;
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
