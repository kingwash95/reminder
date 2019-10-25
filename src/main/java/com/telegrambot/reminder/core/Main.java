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
        Bot bot = new Bot();
        try{
            telegram.registerBot(bot);
        }
        catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }


    }
}

