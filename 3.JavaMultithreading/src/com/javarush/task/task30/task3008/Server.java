package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static void sendBroadcastMessage(Message message){
        try {
            for (Connection connection : connectionMap.values()) {
                connection.send(message);
            }
        } catch (IOException e) {
            ConsoleHelper.writeMessage("message haven't been sent");
        }
    }



    public static void main(String[] args) throws IOException {
        int port = ConsoleHelper.readInt();

        ServerSocket server = new ServerSocket(port);
        try {
            System.out.println("сервер запущен");
            while (true) {
                Socket socket = server.accept();
                Handler handler = new Handler(socket);
                handler.start();
            }
        } catch (Exception e){
                server.close();
                System.out.println("exeption");
        }
    }

    private static class Handler extends Thread{
        private Socket socket;

        Handler(Socket socket) {
            this.socket = socket;
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
            connection.send(new Message(MessageType.NAME_REQUEST));
            Message messageReceive = connection.receive();
            String userName = null;
            if(messageReceive.getType() != MessageType.USER_NAME) userName = serverHandshake(connection);
            if(messageReceive.getData().isEmpty() || connectionMap.containsKey(messageReceive.getData())) {
                userName = serverHandshake(connection);
            }
            else {
                userName = messageReceive.getData();
                connectionMap.put(userName, connection);
                connection.send(new Message(MessageType.NAME_ACCEPTED));
            }
            return userName;
        }

        private void notifyUsers(Connection connection, String userName) throws IOException {
            for (Map.Entry<String, Connection> entry: connectionMap.entrySet()){
                if(!entry.getKey().equals(userName)) {
                    connection.send(new Message(MessageType.USER_ADDED, entry.getKey()));
                }
            }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {
            while (true) {
                Message messageReceive = connection.receive();
                String text = null;
                if (messageReceive.getType() == MessageType.TEXT) {
                    text = String.format("%s: %s", userName, messageReceive.getData());
                    sendBroadcastMessage(new Message(MessageType.TEXT, text));
                } else ConsoleHelper.writeMessage("Unsupported message type");
            }
        }

        @Override
        public void run() {
            ConsoleHelper.writeMessage("Установлено новое соединение с " + socket.getRemoteSocketAddress());

            String userName = null;

            try (Connection connection = new Connection(socket)) {
                userName = serverHandshake(connection);

                // Сообщаем всем участникам, что присоединился новый участник
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));

                // Сообщаем новому участнику о существующих участниках
                notifyUsers(connection, userName);

                // Обрабатываем сообщения пользователей
                serverMainLoop(connection, userName);

            } catch (IOException | ClassNotFoundException e) {
                ConsoleHelper.writeMessage("Ошибка при обмене данными с " + socket.getRemoteSocketAddress());
            }

            if (userName != null) {
                connectionMap.remove(userName);
                sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));
            }

            ConsoleHelper.writeMessage("Соединение с " + socket.getRemoteSocketAddress() + " закрыто.");
        }
    }
}
