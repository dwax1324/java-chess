package controller;

import controller.handler.CommandHandler;
import controller.handler.HandlerMapper;
import java.sql.Connection;
import service.ChessService;
import view.InputView;
import view.OutputView;

public class ChessController {

    private final Connection connection;

    public ChessController(final Connection connection) {
        this.connection = connection;
    }

    public void start() {
        final ChessService chessService = new ChessService(connection);

        while (true) {
            try {
                final String command = InputView.inputCommand();
                if (command.equals("end")) {
                    break;
                }
                final CommandHandler commandHandler = HandlerMapper.findBy(command);

                commandHandler.execute(command, chessService);

                if (!chessService.isRunning()) {
                    break;
                }
            } catch (final Exception e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
//        retryUntilNoException(this::play, chessGame);
//        wrapUp(chessGame);
    }

//
//    private void wrapUp(final ChessGame chessGame) {
//        if (chessGame.isKingDead()) {
//            chessGame.reset();
//            OutputView.printChessResult(DtoMapper.generateGameResultResponse(chessGame.calculateScore(Color.WHITE),
//                    chessGame.calculateScore(Color.BLACK), chessGame.isKingDeadOf(Color.WHITE),
//                    chessGame.isKingDeadOf(Color.BLACK)));
//            OutputView.printGameEndMessage();
//        }
//    }
//
//    private void play(final ChessGame chessGame) {
//        while (chessGame.isRunning()) {
//            final String command = InputView.inputCommand();
//            runCommand(chessGame, command);
//        }
//    }
//
//    private void runCommand(final ChessGame chessGame, final String command) {
//        if (isCommandStatus(command)) {
//            OutputView.printChessResult(DtoMapper.generateGameResultResponse(chessGame.calculateScore(Color.WHITE),
//                    chessGame.calculateScore(Color.BLACK), chessGame.isKingDeadOf(Color.WHITE),
//                    chessGame.isKingDeadOf(Color.BLACK)));
//            return;
//        }
//        chessGame.execute(command);
//        printChessBoardIfRunning(chessGame);
//    }
//
//    private boolean isCommandStatus(final String command) {
//        return command.equals("status");
//    }
//
//    private void printChessBoardIfRunning(final ChessGame chessGame) {
//        if (chessGame.isRunning()) {
//            OutputView.printChessBoard(DtoMapper.generateBoardResponse(chessGame.getSquares()));
//        }
//    }
//
//    private <T> T retryUntilNoException(final Consumer<ChessGame> consumer, final ChessGame chessGame) {
//        try {
//            consumer.accept(chessGame);
//        } catch (final IllegalArgumentException | UnsupportedOperationException | NoSuchElementException exception) {
//            OutputView.printErrorMessage(exception.getMessage());
//            return retryUntilNoException(consumer, chessGame);
//        }
//        return null;
//    }
}
