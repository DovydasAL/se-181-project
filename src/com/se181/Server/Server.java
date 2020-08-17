package com.se181.Server;

import com.se181.clientmodel.PieceColor;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.Executors;

public class Server {
    public static void main(String[] args) throws Exception{
        boolean isFull = false;
        try{
            ServerSocket listener = new ServerSocket(8080);
            System.out.println("Server is running on port 8080");
            var pool = Executors.newFixedThreadPool(2);
            ServerThread client1 = new ServerThread(listener.accept(), PieceColor.BLACK);
            pool.execute(client1);
            ServerThread client2 = new ServerThread(listener.accept(), PieceColor.WHITE);
            pool.execute(client2);
            client1.setOpponent(client2.getNickname());
            client2.setOpponent(client1.getNickname());
            //for(int i = 0; i<2;i++){
            //    pool.execute(new ServerThread(listener.accept(),i+1));
            //}

        }catch (IOException exception){
            exception.printStackTrace();
        }
    }
}
