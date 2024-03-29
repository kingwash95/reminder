package com.botreminder.botreminder.core;

import com.botreminder.botreminder.database.entity.Records;
import com.botreminder.botreminder.database.service.RecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;



public class  Bot extends TelegramLongPollingBot{
    @Autowired
    private RecordsService recordsService;

    private long chatId;
    private String inText;
    private int key = 0;
    private String stringData;
    private String stringReminder;
   // private String stringTime;


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
        Date dateSql = getDate(stringData);
        Records records = new Records(chatId, dateSql, stringReminder);
        recordsService.createRecords(records);
    }

    public Date getDate(String stringData){
        Date dateSql = null;
        try {
            DateFormat dF = new SimpleDateFormat("dd-MM-yyyy"); //data in form is in this format
            java.util.Date datejava = dF.parse(stringData);  // string data is converted into java util date
            DateFormat dFsql = new SimpleDateFormat("yyyy-MM-dd"); //converted date is reformatted for conversion to sql.date
            String ndt = dFsql.format(datejava); // java util date is converted to compatible java sql date
            dateSql = Date.valueOf(ndt);  // finally data from the form is converted to java sql. date for placing in database

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateSql;
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