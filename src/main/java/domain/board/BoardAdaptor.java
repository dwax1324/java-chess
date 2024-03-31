package domain.board;

import domain.piece.Piece;
import observable.Observable;
import observable.Publishable;

public class BoardAdaptor implements Publishable<Piece> {
    private final Board board;
    private Observable observable;

    public BoardAdaptor(final Board board) {
        this.board = board;
    }

    public void move(final String source, final String target) {
        board.move(source, target);
        push(getPiece(target));
    }

    @Override
    public void subscribe(final Observable observable) {
        this.observable = observable;
    }

    @Override
    public void push(final Piece piece) {
        observable.update(piece);
    }

    public Piece getPiece(final String source) {
        return board.getPiece(source);
    }
}
