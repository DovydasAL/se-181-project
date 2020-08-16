package com.se181.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerThread implements Runnable{
    private Socket socket;

    public ServerThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("connected: " + this.socket);
        try {
            //var out = new PrintWriter(socket.getOutputStream(), true);
            //var in = new Scanner(socket.getInputStream());
            //while (in.hasNextLine()) {
            //    out.println(in.nextLine().toUpperCase());
            ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
            outStream.flush();
            ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            System.out.println("Error:" + socket);
            ex.printStackTrace();
        } //finally {
        //    try {
        //        socket.close();
        //    } catch (IOException e) {
        //    }
        //    System.out.println("Closed: " + socket);
        //}
    }
}
