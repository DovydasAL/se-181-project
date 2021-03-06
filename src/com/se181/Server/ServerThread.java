package com.se181.Server;

import com.se181.clientmodel.PieceColor;
import com.se181.clientmodel.Player;
import com.se181.datamodel.*;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerThread implements Runnable{
    private Socket Player1;
    private Socket Player2;
    List<Player> players;
    String whoTurn;
    private ObjectOutputStream toPlayer1;
    private ObjectInputStream fromPlayer1;
    private ObjectOutputStream toPlayer2;
    private ObjectInputStream fromPlayer2 ;
    public ServerThread(Socket player1, Socket player2,ObjectOutputStream toPlayer1,ObjectOutputStream toPlayer2){
        this.toPlayer1 = toPlayer1;
        this.toPlayer2 = toPlayer2;
        this.Player1 = player1;
        this.Player2 = player2;
        this.players = new ArrayList<>();
        this.whoTurn = "";
    }

    protected int classifyRequest(Object request){
        int classify = 0;
//        if (request.getClass() == connectionRequest.class){
//            classify = 1;
        if(request.getClass() == readyRequest.class){
            classify = 2;
        }else if(request.getClass() == gamePlay.class){
            classify  = 3;
        }else {
            classify = 4;
        }
        return classify;
    }
    @Override
    public void run() {
        int c;
        System.out.println("Ready for the game session");
        try {
            fromPlayer1 = new ObjectInputStream(this.Player1.getInputStream());
            fromPlayer2 = new ObjectInputStream(this.Player2.getInputStream());
            readyRequest rReq1 = (readyRequest)fromPlayer1.readObject();
            connectionResponse cRes2 = new connectionResponse(true);
            cRes2.setHasTwo(true);
            toPlayer2.writeObject(cRes2);
            toPlayer2.flush();
            readyRequest rReq2 = (readyRequest)fromPlayer2.readObject();
            Player p1 = new Player(PieceColor.WHITE,rReq1.getNickName());
            this.whoTurn = rReq1.getNickName();
            Player p2 = new Player(PieceColor.BLACK,rReq2.getNickName());
            players.add(p1);
            players.add(p2);
            readyResponse rResp = new readyResponse();
            rResp.setNickNameList(players);
            rResp.setFirstTurn(p1.nickname);
            toPlayer1.writeObject(rResp);
            toPlayer1.flush();
            toPlayer2.writeObject(rResp);
            toPlayer2.flush();
            Object gameplay = null;
            while(true) {
                if (this.whoTurn.equals(players.get(0).nickname)){
                    try {
                        gameplay = fromPlayer1.readObject();
                        this.whoTurn = players.get(1).nickname;
                    } catch (EOFException ex){
                        System.out.println(players.get(0).nickname + " has disconnected.");
                        fromPlayer1.close();
                    }
                }else{
                    try {
                        gameplay = fromPlayer2.readObject();
                        this.whoTurn = players.get(0).nickname;
                    } catch (EOFException ex){
                        System.out.println(players.get(1).nickname + " has disconnected.");
                        fromPlayer2.close();
                    }
                }
                if(classifyRequest(gameplay) == 3){
                    gamePlay gRes = (gamePlay)gameplay;
                    System.out.println(gRes.nextTurn);
                    try {
                        toPlayer1.writeObject(gRes);
                        toPlayer1.flush();
                    } catch (Exception ex) {
                        System.out.println(players.get(0).nickname + " has disconnected.");
                    }
                    try {
                        toPlayer2.writeObject(gRes);
                        toPlayer2.flush();
                    } catch (Exception ex) {
                        System.out.println(players.get(0).nickname + " has disconnected.");
                    }
                    if(!gRes.restart && gRes.nextTurn.equals("")){
                        toPlayer1.writeObject(gRes);
                        toPlayer1.flush();
                        toPlayer2.writeObject(gRes);
                        toPlayer2.flush();
                        Player1.close();
                        Player2.close();
                        fromPlayer1.close();
                        fromPlayer2.close();
                        toPlayer1.close();
                        toPlayer2.close();
                        break;
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println("Error");
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



}
