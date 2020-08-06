
import java.awt.*;
import java.util.Collection;
import java.util.List;

public abstract class Piece {

        final PieceType pieceType;
        final Color pieceColor;
        final int piecePosition;


        Piece(final PieceType type,
              final Color color,
              final int piecePosition
              ) {
            this.pieceType = type;
            this.piecePosition = piecePosition;
            this.pieceColor = color;
        }

        public PieceType getPieceType() {
            return this.pieceType;
        }

        public Color getPieceAllegiance() {
            return this.pieceColor;
        }

        public int getPiecePosition() {
            return this.piecePosition;
        }
        public abstract List<Move> findLegalMoves(final Board board);

    }

