package domain.board;

import domain.piece.Color;
import domain.piece.Piece;
import observable.Observable;

public class ChessGame implements Observable<Piece> {

    private final BoardAdaptor board;
    private Color activeColor;
    private boolean finished;

    public ChessGame(final BoardAdaptor board) {
        this.board = board;
        this.activeColor = Color.WHITE;
        this.finished = false;
    }

    public void move(final String source, final String target) {
        if (finished) {
            throw new IllegalCallerException("게임이 종료되어 더이상 움직일 수 없습니다");
        }
        validateCurrentColor(board.getPiece(source));
        board.move(source, target);
    }

    private void validateCurrentColor(final Piece currentPiece) {
        if (!currentPiece.hasColor(activeColor)) {
            throw new IllegalArgumentException(
                    String.format("현재 차례: %s, 현재 차례의 말만 움직일 수 있습니다", activeColor.name()));
        }
    }

    public Color getColor() {
        return this.activeColor;
    }

    @Override
    public void update(final Piece piece) {
        if (piece.isKing()) {
            finished = true;
            return;
        }
        activeColor = activeColor.reverse();
    }
}
