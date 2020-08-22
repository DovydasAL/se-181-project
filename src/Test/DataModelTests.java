package Test;

import com.se181.clientmodel.Board;
import com.se181.clientmodel.PieceColor;
import com.se181.clientmodel.Player;
import com.se181.datamodel.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataModelTests {

    @Test
    void connectionRequestTest() {
        connectionRequest CR = new connectionRequest();
    }

    @Test
    void connectionResponseTest(){
        //regular constructor
        connectionResponse CR = new connectionResponse();
        //Parameterized constructor
        connectionResponse CRP = new connectionResponse(false);
        CRP.getConnected();
        CRP.setConnected(true);
        CRP.getHasTwo();
        CRP.setHasTwo(true);
        assertEquals(CRP.getHasTwo(),true);


    }



    @Test
    void gamePlayTest(){
        //Default constructor
        gamePlay GP = new gamePlay();

        //Parameterized constructor
        //parameters
        Board chessBoard = new Board();
        gamePlay GPP = new gamePlay(chessBoard,"yes","Black", false);

        //methods
        GPP.getChessBoard();
        GP.setChessBoard(chessBoard);
        GP.getHasWon();
        GP.setHasWon("yes");
        GP.getNextTurn();
        GP.setNextTurn("White");
        GP.getRestart();
        GP.setRestart(false);


    }
    @Test
    void quitTest(){
        //Extends GamePlay
        quit Q = new quit();
        Q.isQuit();
        Q.setQuit(true);

    }
    @Test
    void readyRequestTest(){
        readyRequest RQ = new readyRequest();
        //Parameterized constructor
        readyRequest RQP = new readyRequest(true, "");
        RQP.setNickName("Kanye");
        RQP.getNickName();
        RQP.getReady();
        //set ready is private will need to make public to test here
        //RQP.setReady(true);
    }


    @Test
    void readyResponseTest(){
        //Default constructor
        readyResponse RP = new readyResponse();

        //Parameterized constructor
        // params
        Player P1 = new Player(PieceColor.WHITE, "Kanye");
        Player P2 = new Player(PieceColor.BLACK, "Trump");

        List<Player> playerList = new ArrayList<>();
        playerList.add(P1);
        playerList.add(P2);

        readyResponse RPP = new readyResponse("White",playerList);
        RPP.getFirstTurn();
        RPP.setFirstTurn("White");
        RPP.getNickNameList();
        RPP.setNickNameList(playerList);






    }


}
