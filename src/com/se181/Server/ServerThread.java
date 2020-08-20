package com.se181.Server;

import com.se181.clientmodel.PieceColor;
import com.se181.clientmodel.Player;
import com.se181.datamodel.*;

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
    public ServerThread(Socket player1, Socket player2){
        this.Player1 = player1;
        this.Player2 = player2;
        this.players = new ArrayList<>();
        this.whoTurn = "";
    }

    protected int classifyRequest(Object request){
        int classify = 0;
        if (request.getClass() == connectionRequest.class){
            classify = 1;
        }else if(request.getClass() == readyRequest.class){
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
            toPlayer1 = new ObjectOutputStream(this.Player1.getOutputStream());
            toPlayer1.flush();
            fromPlayer1 = new ObjectInputStream(this.Player1.getInputStream());
            toPlayer2 = new ObjectOutputStream(this.Player2.getOutputStream());
            toPlayer2.flush();
            fromPlayer2 = new ObjectInputStream(this.Player2.getInputStream());
            toPlayer1.reset();
            toPlayer2.reset();
            var rReq1 = (readyRequest)fromPlayer1.readObject();
            var cRes2 = new connectionResponse(true);
            cRes2.setHasTwo(true);
            toPlayer2.writeObject(cRes2);
            var rReq2 = (readyRequest)fromPlayer2.readObject();
            Player p1 = new Player(PieceColor.WHITE,rReq1.getNickName());
            this.whoTurn = rReq1.getNickName();
            Player p2 = new Player(PieceColor.BLACK,rReq2.getNickName());
            players.add(p1);
            players.add(p2);
            var rResp = new readyResponse();
            rResp.setNickNameList(players);
            rResp.setFirstTurn(p1.nickname);
            toPlayer1.writeObject(rResp);
            toPlayer2.writeObject(rResp);
            Object gameplay = null;
            while(true) {
                if (this.whoTurn.equals(players.get(0).nickname)){
                    gameplay = fromPlayer1.readObject();
                    this.whoTurn = players.get(1).nickname;
                }else{
                    gameplay = fromPlayer2.readObject();
                    this.whoTurn = players.get(0).nickname;
                }
                if(classifyRequest(gameplay) == 3){
                    toPlayer1.writeObject(gameplay);
                    toPlayer2.writeObject(gameplay);
                }else if(classifyRequest(gameplay) == 4){
                    var qRes = new quit();
                    qRes.setQuit(true);
                    toPlayer1.writeObject(qRes);
                    toPlayer2.writeObject(qRes);
                    break;
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
