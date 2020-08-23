package com.se181.Server;

import com.se181.clientmodel.PieceColor;
import com.se181.clientmodel.Player;
import com.se181.datamodel.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public static void main(String[] args) throws Exception{
        try{
            ServerSocket listener = new ServerSocket(8080);
            System.out.println("Server is running on port 8080");
            while(true){
                Socket player1  = listener.accept();
                ObjectOutputStream outStream = new ObjectOutputStream(player1.getOutputStream());
                outStream.flush();
                connectionResponse connResponse = new connectionResponse(true);
                System.out.println("Connected:  "+ player1);
                outStream.writeObject(connResponse);
                outStream.flush();
                Socket player2 = listener.accept();
                System.out.println("Connected:  "+ player2);
                ObjectOutputStream outStream1 = new ObjectOutputStream(player2.getOutputStream());
                connectionResponse connResponse1 = new connectionResponse(true);

                outStream1.writeObject(connResponse1);
                outStream1.flush();
                connResponse1.setHasTwo(true);
                outStream.writeObject(connResponse1);
                outStream.flush();
                Thread gameHandler = new Thread(new ServerThread(player1,player2,outStream,outStream1));
                gameHandler.start();

            }

        }catch (IOException exception){
            exception.printStackTrace();
        }
    }
}
