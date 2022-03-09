package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BotClient extends Client {

    public static void main(String[] args) {
        BotClient botClient = new BotClient();
        botClient.run();
    }

    @Override
    protected String getUserName() {
        return String.format("date_bot_%d", (int) (Math.random() * 100));
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    public class BotSocketThread extends SocketThread {

        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
            String[]text = message.split(": ");
            if(text.length != 2) return;
            SimpleDateFormat simpleDateFormat = null;
            switch (text[1]) {
                case "дата":
                    simpleDateFormat = new SimpleDateFormat("d.MM.YYYY");
                    break;
                case "день":
                    simpleDateFormat = new SimpleDateFormat("d");
                    break;
                case "месяц":
                    simpleDateFormat = new SimpleDateFormat("MMMM");
                    break;
                case "год":
                    simpleDateFormat = new SimpleDateFormat("YYYY");
                    break;
                case "время":
                    simpleDateFormat = new SimpleDateFormat("H:mm:ss");
                    break;
                case "час":
                    simpleDateFormat = new SimpleDateFormat("H");
                    break;
                case "минуты":
                    simpleDateFormat = new SimpleDateFormat("m");
                    break;
                case "секунды":
                    simpleDateFormat = new SimpleDateFormat("s");
            }
            if(simpleDateFormat != null)
                BotClient.this.sendTextMessage(String.format("Информация для %s: ", text[0]) + simpleDateFormat.format(Calendar.getInstance().getTime()));
        }
    }
}
