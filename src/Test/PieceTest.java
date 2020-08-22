//package Test;

import com.se181.clientmodel.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api. Assertions.assertEquals;
public class PieceTest {
    public Board board = new Board();

    //ROOK
    @Test
    void validMovesRook() {
        //clearing the white pieces of the board
        board.whiteSet.pieces.clear();
        board.blackSet.pieces.clear();

        //add the piece to the (6 ,3) square
        Square S = new Square(3, 4);
        ChessPiece rook = new Rook(PieceColor.WHITE, S);
        board.whiteSet.pieces.add(rook);

        //starts from the right most valid-move column wise and moves to the upper row
        List<Square> validMoves;
        validMoves = board.getPieceAt(S, PieceColor.WHITE).validMoves(board);
        //one row up
        assertEquals(validMoves.get(0).col, 4);
        assertEquals(validMoves.get(0).row, 2);
        //two row up
        assertEquals(validMoves.get(1).col, 4);
        assertEquals(validMoves.get(1).row, 1);


    }

    //Bishop
    @Test
    void validMovesBishop() {
        //clearing the white pieces of the board
        board.whiteSet.pieces.clear();
        board.blackSet.pieces.clear();

        //add the piece to the (6 ,3) square
        Square S = new Square(3, 4);
        ChessPiece bishop = new Bishop(PieceColor.WHITE, S);
        board.whiteSet.pieces.add(bishop);

        //starts from the right most valid-move column wise and moves to the upper row
        List<Square> validMoves;
        validMoves = board.getPieceAt(S, PieceColor.WHITE).validMoves(board);

        //+7
        assertEquals(validMoves.get(0).col, 3);
        assertEquals(validMoves.get(0).row, 2);


        //+7
        assertEquals(validMoves.get(1).col, 2);
        assertEquals(validMoves.get(1).row, 1);


    }
    //Knight

    @Test
    void validMovesKnight() {
        //clearing the white pieces of the board
        board.whiteSet.pieces.clear();
        board.blackSet.pieces.clear();

        //add the piece to the (6 ,3) square
        Square S = new Square(3, 4);
        ChessPiece knight = new Knight(PieceColor.WHITE, S);
        board.whiteSet.pieces.add(knight);

        //starts from the right most valid-move column wise and moves to the upper row
        List<Square> validMoves;
        validMoves = board.getPieceAt(S, PieceColor.WHITE).validMoves(board);

        //code :knightOffsets.add(new Knight.KnightOffset(-1, -2));

        assertEquals(validMoves.get(0).row, 2);
        assertEquals(validMoves.get(0).col, 2);

        //code : knightOffsets.add(new Knight.KnightOffset(-1, 2));

        assertEquals(validMoves.get(1).row, 2);
        assertEquals(validMoves.get(1).col, 6);


    }


    //King
    @Test
    void validMovesKing() {
        //clearing the white pieces of the board
        board.whiteSet.pieces.clear();
        board.blackSet.pieces.clear();

        //add the piece to the (6 ,3) square
        Square S = new Square(3, 4);
        ChessPiece king = new King(PieceColor.WHITE, S);
        board.whiteSet.pieces.add(king);

        //starts from the right most valid-move column wise and moves to the upper row
        List<Square> validMoves;
        validMoves = board.getPieceAt(S, PieceColor.WHITE).validMoves(board);


        //code
//        for (int i=-1;i<=1;i++) {
//            for (int j=-1;j<=1;j++) {
//                Square move = new Square(this.position.row + i, this.position.col + j);
//                if (!(i == 0 && j == 0)) {
//                    handlePosition(move, validMoves, board, this.color);
//                }
//            }
        assertEquals(validMoves.get(0).col, 3);
        assertEquals(validMoves.get(0).row, 2);

        assertEquals(validMoves.get(1).col, 4);
        assertEquals(validMoves.get(1).row, 2);


    }

    //Queen
    @Test
    void validMovesQueen() {
        //clearing the white pieces of the board
        board.whiteSet.pieces.clear();
        board.blackSet.pieces.clear();

        //add the piece to the (6 ,3) square
        Square S = new Square(3, 4);
        ChessPiece queen = new Queen(PieceColor.WHITE, S);
        board.whiteSet.pieces.add(queen);

        //starts from the right most valid-move column wise and moves to the upper row
        List<Square> validMoves;
        validMoves = board.getPieceAt(S, PieceColor.WHITE).validMoves(board);


        assertEquals(validMoves.get(0).col, 4);
        assertEquals(validMoves.get(0).row, 2);

        assertEquals(validMoves.get(1).col, 4);
        assertEquals(validMoves.get(1).row, 1);


    }

    @Test
    void pawnTest(){

    }

    //    //we can't test game as we will need to run it same as the server
//    @Test
//    void gameTest() throws Exception {
//
//
//         String serverIP;
//         Board board;
//         Square lastClickedTile;
//         Player player;
//         Player opponent;
//         Boolean playersTurn;
//         ArrayList<Square> moveHistory;
//         Boolean restart;
//
//        Socket socket;
//        ObjectOutputStream outStream;
//         ObjectInputStream inStream;
//        serverIP = "";
//        board = new Board();
//        lastClickedTile = null;
//        player = new Player(WHITE, "test1");
//        opponent = new Player(BLACK, "test2");
//        playersTurn = false;
//        moveHistory = new ArrayList<Square>();
//        restart = false;
//        socket = new Socket("localhost", 8080);
//        outStream = new ObjectOutputStream(socket.getOutputStream());
//        outStream.flush();
//        inStream = new ObjectInputStream(socket.getInputStream());
//        Game game = new Game(serverIP, board, lastClickedTile, player, opponent,playersTurn,moveHistory,restart);
////        Game game = new Game();
//



    }

