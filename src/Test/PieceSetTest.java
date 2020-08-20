package Test;
import com.se181.clientmodel.PieceColor;
import com.se181.clientmodel.PieceSet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PieceSetTest {
    protected PieceSet pieceSet = new PieceSet(PieceColor.WHITE);
    @Test
    void shallowCopy() {
        PieceSet copy = pieceSet.shallowCopy();
        assertEquals(16,copy.pieces.size());
    }
}