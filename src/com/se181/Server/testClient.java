package com.se181.Server;

import com.se181.datamodel.*;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class testClient {
    protected static int classifyResponse(Object response){
        int classify = 0;
        if (response.getClass() == connectionResponse.class){
            classify = 1;
        }else if(response.getClass() == readyResponse.class){
            classify = 2;
        }else if(response.getClass() == gamePlay.class){
            classify  = 3;
        }else {
            classify = 4;
        }
        return classify;
    }
    public static void main(String[] args) throws Exception{
        try (var socket = new Socket("127.0.0.1", 8080)) {
            ObjectOutputStream toServer = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream fromServer = new ObjectInputStream(socket.getInputStream());
            toServer.flush();
            Object res = fromServer.readObject();
            if(classifyResponse(res) == 1) {
                System.out.println("tested");
            }


        }
    }
}
