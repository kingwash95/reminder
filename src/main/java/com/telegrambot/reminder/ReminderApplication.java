package com.telegrambot.reminder;

import com.telegrambot.reminder.core.Bot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

@SpringBootApplication
public class ReminderApplication {

	public static void main(String[] args) {
		ApiContextInitializer.init();
		SpringApplication.run(ReminderApplication.class, args);
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
