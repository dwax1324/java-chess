package controller.handler;


import service.ChessService;

public interface CommandHandler {
    void execute(CommandHandler commandHandler, ChessService chessService);
}
