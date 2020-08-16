package com.se181.Server;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class testClient {
       public static void main(String[] args) throws Exception{
           try (var socket = new Socket("127.0.0.1", 8008)) {
               System.out.println("Enter lines of text then Ctrl+D or Ctrl+C to quit");
               var scanner = new Scanner(System.in);
               var in = new Scanner(socket.getInputStream());
               var out = new PrintWriter(socket.getOutputStream(), true);
               while (scanner.hasNextLine()) {
                   System.out.println(scanner.nextLine());
                   System.out.println(in.nextLine());
               }
           }
       }
}
