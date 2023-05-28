/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.client_server;

import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author marce
 */
public class ChatClient {

    static JFrame chatWindow = new JFrame("Meu Chat");
    static JTextArea chatMessages = new JTextArea(22, 40);
    static JTextField chatNewMessage = new JTextField(40);
    static JButton chatSendButton = new JButton("Enviar");
    static BufferedReader inputMessages;
    static PrintWriter outputMessages;
    static JLabel chatUserLogin = new JLabel("");

    public ChatClient() {
        chatWindow.setLayout(new FlowLayout());
        chatWindow.add(chatUserLogin);
        chatWindow.add(new JScrollPane(chatMessages));
        chatWindow.add(chatNewMessage);
        chatWindow.add(chatSendButton);

        chatWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chatWindow.setSize(475, 500);
        chatWindow.setVisible(true);
        chatMessages.setEditable(false);
        chatNewMessage.setEditable(false);
        
        chatSendButton.addActionListener(new ListenerMessage());
        chatNewMessage.addActionListener(new ListenerMessage());
        
        
    }

    public void startChat() throws Exception {
        String endressIp = JOptionPane.showInputDialog(chatWindow, "Endereço IP do Servidor", "Informação",
                JOptionPane.PLAIN_MESSAGE);
        Socket userChat = new Socket(endressIp, 9086);
        inputMessages = new BufferedReader(new InputStreamReader(userChat.getInputStream()));
        outputMessages = new PrintWriter(userChat.getOutputStream(),true);
        
        while(true){
            String msg = inputMessages.readLine();
            
            if(msg.equals("NOME_REQUERIDO")){
                String name = JOptionPane.showInputDialog(chatWindow,"Nome do Usuário:",
                        "Informação",JOptionPane.PLAIN_MESSAGE);
                outputMessages.println(name);
            }else if(msg.equals("NOME_EXISTENTE")){
                String name = JOptionPane.showInputDialog(chatWindow,"Informe outro nome do usuário:",
                        "Nome duplicado!",JOptionPane.PLAIN_MESSAGE);
                outputMessages.println(name);
            }else if(msg.startsWith("NOME_ACEITO")){
                chatNewMessage.setEditable(true);
                chatUserLogin.setText("Você esta Logado como: " + msg.substring(11));
                
            }else{
                chatMessages.append(msg + "\n");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ChatClient chat = new ChatClient();
        chat.startChat();

    }
    
    class ListenerMessage implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event){
            ChatClient.outputMessages.println(ChatClient.chatNewMessage.getText());
            ChatClient.chatNewMessage.setText("");
        }
    }

}
