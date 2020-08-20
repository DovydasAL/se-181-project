package com.se181.Server;

import com.se181.datamodel.connectionResponse;
import com.se181.datamodel.gamePlay;
import com.se181.datamodel.readyRequest;
import com.se181.datamodel.readyResponse;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class testClient2 {
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
        Scanner sc = new Scanner(System.in);
        try (var socket = new Socket("127.0.0.1", 8080)) {
            ObjectOutputStream toServer = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream fromServer = new ObjectInputStream(socket.getInputStream());
            toServer.flush();
            while(true){
                Object res = fromServer.readObject();
                if(classifyResponse(res) == 1){
                    var resp = (connectionResponse) res;
                    System.out.println("tested");
                    if(resp.getHasTwo()){
                        var rReq = new readyRequest(true);
                        String nickname = sc.nextLine();
                        rReq.setNickName(nickname);
                        toServer.writeObject(rReq);
                        toServer.flush();
                    }
                }else if(classifyResponse(res) == 2){
                    var response = (readyResponse) res;
                    System.out.println(response.getNickNameList().get(0).nickname);
                    System.out.println(response.getNickNameList().get(0).color.toString());
                    System.out.println(response.getNickNameList().get(1).nickname);
                    System.out.println(response.getNickNameList().get(1).color.toString());
                }

            }

        }
    }
}