package com.botreminder.botreminder;

import com.botreminder.botreminder.core.Bot;
import com.botreminder.botreminder.database.entity.Records;
import com.botreminder.botreminder.database.service.RecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

@SpringBootApplication
public class BotreminderApplication {


	public static void main(String[] args) {
		ApiContextInitializer.init();
		SpringApplication.run(BotreminderApplication.class, args);
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
