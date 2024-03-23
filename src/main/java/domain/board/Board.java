package domain.board;

import domain.piece.Empty;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.info.Color;
import domain.piece.info.Position;
import domain.piece.info.Vector;
import java.util.List;
import java.util.Map;

public class Board {
    private Color turn;
    private final Map<Position, Piece> squares;

    public Board(final Map<Position, Piece> squares) {
        this.turn = Color.WHITE;
        this.squares = squares;
    }

    public void move(final Position source, final Position target) {
        final Piece currentPiece = squares.get(source);
        final Piece targetPiece = squares.get(target);
        final Vector vector = new Vector(source, target);

        validateMovement(currentPiece, vector, targetPiece);

        if (isPiecesPossiblyExistOnPath(vector)) {
            validateNoPieceExistInPath(vector, source);
        }
        updateBoard(source, target, currentPiece);
        switchTurn();
    }

    private void validateMovement(final Piece currentPiece, final Vector vector, final Piece targetPiece) {
        validateTurn(currentPiece);
        validateReachability(vector, currentPiece, targetPiece);

    }

    private boolean isPiecesPossiblyExistOnPath(final Vector vector) {
        return vector.hasAbsoluteValueMoreOrEqualThan(2) && vector.isStraightOrDiagonal();
    }

    private void validateReachability(final Vector vector, final Piece currentPiece,
                                      final Piece targetPiece) {
        if (!currentPiece.isReachable(vector, targetPiece)) {
            throw new IllegalArgumentException();
        }
    }

    private void validateNoPieceExistInPath(final Vector vector, final Position source) {
        // 룩, 비숍, 퀸, 폰(두 칸),

        final List<Position> path = vector.generatePathExcludingEndpoints(source);
//        path.stream()
//                .map(squares::get)
//                .filter(r -> !r.isEmpty())
//                .findAny()
//                .orElseThrow(() -> new IllegalArgumentException("이동할 수 없는 경로입니다"));
    }

    private void validateTurn(final Piece currentPiece) {
        if (!currentPiece.hasColor(turn)) {
            throw new IllegalArgumentException("현재 차례가 아닙니다.");
        }
    }

    private void updateBoard(final Position source, final Position target, final Piece currentPiece) {
        squares.remove(target);

        if (currentPiece.isInitPawn()) {
            squares.put(target, new Pawn(turn));
        } else {
            squares.put(target, currentPiece);
        }

        squares.put(source, Empty.INSTANCE);
    }

    private void switchTurn() {
        turn = turn.reverse();
    }

    public Map<Position, Piece> squares() {
        return squares;
    }
}
