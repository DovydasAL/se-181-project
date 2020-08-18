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
    private Socket socket;
    private String nickname;
    private String opponent;
    private PieceColor color;

    public ServerThread(Socket socket, PieceColor c){
        this.socket = socket;
        this.color = c;
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
        System.out.println("connected: " + this.socket);
        try {
            ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
            outStream.flush();
            ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
            while(true) {
                Object request = inStream.readObject();
                c = classifyRequest(request);
                if (c == 1) {
                    connectionRequest cRequest = (connectionRequest) request;
                    connectionResponse cResponse = new connectionResponse();
                    setNickname(cRequest.getNickName());
                    cResponse.setConnected(true);
                    outStream.writeObject(cResponse);
                    outStream.flush();
                } else if (c == 2) {
                    readyRequest cRequest = (readyRequest) request;
                    readyResponse cResponse = new readyResponse();
                    if (this.opponent == null){
                        List<Player> players = new ArrayList<>();
                        Player player1 = new Player(this.color,this.nickname);
                        players.add(player1);
                        cResponse.setNickNameList(players);
                    }else{
                        List<Player> players = new ArrayList<>();
                        Player player1 = new Player(this.color,this.nickname);
                        players.add(player1);
                        if(this.color == PieceColor.WHITE){
                            Player player2 = new Player(PieceColor.BLACK,this.opponent);
                            players.add(player2);
                        }else{
                            Player player2 = new Player(PieceColor.WHITE,this.opponent);
                            players.add(player2);
                        }
                        int random_int = (int)(Math.random() * (1 - 0 + 1) + 0);
                        if(random_int == 0){
                            cResponse.setFirstTurn(this.nickname);
                        }else {
                            cResponse.setFirstTurn(this.opponent);
                        }
                        cResponse.setNickNameList(players);
                    }
                    outStream.writeObject(cResponse);
                    outStream.flush();
                }else if (c == 3){
                    gamePlay cRequest = (gamePlay) request;
                    outStream.writeObject(cRequest);
                    outStream.flush();
                }else if (c==4){
                    quit cRequest = (quit)request;
                    outStream.writeObject(cRequest);
                    outStream.flush();
                }
            }
        } catch (IOException ex) {
            System.out.println("Error:" + socket);
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getOpponent() {
        return opponent;
    }

    public void setOpponent(String opponent) {
        this.opponent = opponent;
    }
}
