package com.se181.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.Executors;

public class Server {
    public static void main(String[] args) throws Exception{
        try{
            ServerSocket listener = new ServerSocket(8080);
            System.out.println("Server is running on port 8080");
            var pool = Executors.newFixedThreadPool(2);
            while (true){
                pool.execute(new ServerThread(listener.accept()));
            }
        }catch (IOException exception){
            exception.printStackTrace();
        }
    }
}
