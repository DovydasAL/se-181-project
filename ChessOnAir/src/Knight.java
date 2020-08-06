import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public final class Knight extends Piece {

    private final static int[] POSSIBLE_MOVES = {-17, -15, -10, -6, 6, 10, 15, 17};

    public Knight(final Color color,
                  final int piecePosition) {
        super(PieceType.KNIGHT, color, piecePosition, true);
    }

    public Knight(final Color color,
                  final int piecePosition,
                  final boolean isFirstMove) {
        super(PieceType.KNIGHT, color, piecePosition, isFirstMove);
    }

    @Override
    public List<Move> findLegalMoves(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        for (final int distance : POSSIBLE_MOVES) {
            if (isFirstColumnExclusion(this.piecePosition, distance) ||
                    isSecondColumnExclusion(this.piecePosition, distance) ||
                    isSeventhColumnExclusion(this.piecePosition, distance) ||
                    isEighthColumnExclusion(this.piecePosition, distance)) {
                continue;
            }
            final int des = this.piecePosition + distance;
            if (BoardUtils.isValidTileCoordinate(des)) {
                final Piece pieceAtDes = board.getPiece(des);
                if (pieceAtDes == null) {
                    legalMoves.add(new MajorMove(board, this, des));
                } else {
                    final Color pieceAtDesColor = pieceAtDes.getPieceColor();
                    if (this.pieceColor != pieceAtDesColor) {
                        legalMoves.add(new MajorAttackMove(board, this, des,
                                pieceAtDes));
                    }
                }
            }
        }
        return legalMoves;
    }
}