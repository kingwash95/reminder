package com.telegrambot.reminder.core;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Main {
    public static void main(String[] args) {


        ApiContextInitializer.init();
        TelegramBotsApi telegram = new TelegramBotsApi();

        try{
            telegram.registerBot(new Bot());
        }
        catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }


    }
}

