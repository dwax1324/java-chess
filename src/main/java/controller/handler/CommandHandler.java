package controller.handler;


import service.ChessService;

public interface CommandHandler {
    void execute(final String command, ChessService chessService);
}
