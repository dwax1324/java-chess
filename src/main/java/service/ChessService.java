package service;

import db.Movement;
import db.MovementDao;
import domain.board.Board;
import domain.board.BoardInitiator;
import domain.board.position.Position;
import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ChessService {
    private final MovementDao movementDao;

    public ChessService(final Connection connection) {
        this.movementDao = new MovementDao(connection);
    }

    public void update(final String source, final String target, final String type, final String color) {
        movementDao.createMovement(new Movement(Position.from(source), Position.from(target), type, color));
    }

    public List<Entry<Position, Position>> findPositions() {
        return movementDao.findAll().stream().map(movement -> Map.entry(movement.source(), movement.target())).toList();
    }

    public boolean isRunning() {
        final List<Movement> positions = movementDao.findAll();
        final Board board = new Board(BoardInitiator.init());
        for (final var movement : positions) {
            board.move(movement.source(), movement.target());
        }
        return !board.isKingDead();
    }

    public Board getBoard() {
        final List<Movement> positions = movementDao.findAll();
        final Board board = new Board(BoardInitiator.init());
        for (final var movement : positions) {
            board.move(movement.source(), movement.target());
        }
        return board;
    }

//    public void execute(final String value) {
//        this.commandHandler = this.commandHandler.next(Commands.from(value));
//
//        if (this.commandHandler.isMove()) {
//            moveBoard(value);
//
//        }
//        if (board.isKingDead()) {
//            this.commandHandler = commandHandler.next(EndHandler.getInstance());
//        }
//    }

//    private void moveBoard(final String value) {
//        final StringTokenizer tokens = skipFirstToken(value);
//        final String source = tokens.nextToken();
//        final String target = tokens.nextToken();
//        board.move(source, target);
//        chessService.update(source, target, board.getPiece(target).getName(), board.getPiece(target).getColor().name());
//    }
//
//    public void recover() {
//        final List<Entry<Position, Position>> positions = chessService.findPositions();
//        for (final var movement : positions) {
//            board.move(movement.getKey(), movement.getValue());
//        }
//    }
//
//    private StringTokenizer skipFirstToken(final String command) {
//        final StringTokenizer stringTokenizer = new StringTokenizer(command);
//        stringTokenizer.nextToken();
//        return stringTokenizer;
//    }
//
//
//    public Double calculateScore(final Color color) {
//        return board.calculateScore(color);
//    }
//
//
//    public boolean isKingDead() {
//        return board.isKingDead();
//    }
//
//    public boolean isKingDeadOf(final Color color) {
//        return board.isKingDeadOf(color);
//    }
//
//    public void reset() {
//        chessService.deleteAll();
//    }
//
//    public Map<Position, Piece> getSquares() {
//        return Collections.unmodifiableMap(board.getSquares());
//    }
//
//    public Color getColor() {
//        return board.getColor();
//    }
//
//    public void deleteAll() {
//        movementDao.deleteAll();
//    }
}
