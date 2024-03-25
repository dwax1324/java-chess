package controller;

import domain.ChessGame;
import dto.DtoMapper;
import java.util.NoSuchElementException;
import java.util.function.Consumer;
import view.InputView;
import view.OutputView;

public class ChessController {
    public void start() {
        final ChessGame chessGame = new ChessGame();
        OutputView.printGameStartMessage();
        retryUntilNoException(this::play, chessGame);
    }

    private void play(final ChessGame chessGame) {
        while (chessGame.isRunning()) {
            chessGame.fromCommand(InputView.inputCommand());
            printChessBoard(chessGame);
        }
    }

    private void printChessBoard(final ChessGame chessGame) {
        if (chessGame.isRunning()) {
            OutputView.printChessBoard(DtoMapper.generateBoardResponse(chessGame.getSquares()));
        }
    }

    private <T> T retryUntilNoException(final Consumer<ChessGame> consumer, final ChessGame chessGame) {
        try {
            consumer.accept(chessGame);
        } catch (final IllegalArgumentException | UnsupportedOperationException | NoSuchElementException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return retryUntilNoException(consumer, chessGame);
        }
        return null;
    }
}
