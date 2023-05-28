/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
/**
 *
 * @author marce
 */
package app.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.ArrayList;

public class ChatServer {

    static ArrayList<String> userList = new ArrayList<>();
    static ArrayList<PrintWriter> printWriters = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        System.out.println("Aguardando novos usuários...");
        InetAddress host = InetAddress.getByName("192.168.15.13"); // Substitua pelo seu endereço IP
        int port = 9086; // Substitua pela porta desejada
        ServerSocket sv = new ServerSocket(port, 50, host);
        //ServerSocket sv = new ServerSocket(9086);

        while (true) {
            Socket user = sv.accept();
            System.out.println("Usuário conectado!");

            ChatDriver chat = new ChatDriver(user);
            chat.start();
        }
    }
}

class ChatDriver extends Thread {

    Socket userChat;
    BufferedReader inputData;
    PrintWriter outputData;
    String name;

    public ChatDriver(Socket userChat) throws IOException {
        this.userChat = userChat;
    }

    @Override
    public void run() {
        try {
            inputData = new BufferedReader(new InputStreamReader(userChat.getInputStream()));
            outputData = new PrintWriter(userChat.getOutputStream(), true);

            int counter = 0;

            while (true) {
                if (counter > 0) {
                    outputData.println("NOME_EXISTENTE");
                } else {
                    outputData.println("NOME_REQUERIDO");
                }
                name = inputData.readLine();

                if (name == null) {
                    return;
                }
                //If name of user is not contain in user list this condition will add
                if (!ChatServer.userList.contains(name)) {
                    ChatServer.userList.add(name);
                    break;
                }
                counter++;
            }

            outputData.println("NOME_ACEITO" + name);
            ChatServer.printWriters.add(outputData);

            while (true) {
                String msg = inputData.readLine();

                if (msg == null) {
                    return;
                }

                for (PrintWriter writer : ChatServer.printWriters) {
                    writer.println(name + ":" + msg);
                }
            }

        } catch (Exception ex) {
            System.out.println("Erro no Servidor: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
