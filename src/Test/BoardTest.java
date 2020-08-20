package Test;

import com.se181.clientmodel.Board;
import com.se181.clientmodel.ChessPiece;
import com.se181.clientmodel.PieceColor;
import com.se181.clientmodel.Square;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    public Board board = new Board();

    @Test
    void containsPieceAt() {
        Square position = new Square(7,0);
        PieceColor color = board.containsPieceAt(position);
        assertEquals(color, PieceColor.WHITE);
    }

    @Test
    void containsPieceAt1() {
        Square position = new Square(0,0);
        PieceColor color = board.containsPieceAt(position);
        assertEquals(color, PieceColor.BLACK);
    }
    @Test
    void flipBoard() {
        Board flipped = board.flipBoard();
        assertEquals(6,flipped.whiteSet.pieces.get(0).position.row);
    }
    @Test
    void flipBoard1() {
        Board flipped = board.flipBoard();
        assertEquals(6,flipped.blackSet.pieces.get(0).position.row);
    }

    @Test
    void calculateAllPossibleAttackMove() {
        List<Square> moves = board.calculateAllPossibleAttackMove(PieceColor.BLACK);
        assertEquals(-1, moves.get(0).row);
        assertEquals(-1, moves.get(0).col);

    }

    @Test
    void getPieceAt() {
        Square position = new Square(7,0);
        PieceColor color = PieceColor.WHITE;
        ChessPiece piece = board.getPieceAt(position,color);
        assertEquals(false, piece.Captured);

    }

    @Test
    void getPieceAt1() {
        Square position = new Square(5,0);
        PieceColor color = PieceColor.WHITE;
        ChessPiece piece = board.getPieceAt(position,color);
        assertEquals(null, piece);

    }
}