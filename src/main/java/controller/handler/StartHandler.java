package controller.handler;

import dto.DtoMapper;
import service.ChessService;
import view.OutputView;

public class StartHandler implements CommandHandler {
    @Override
    public void execute(final String command, final ChessService chessService) {
        OutputView.printGameStartMessage();
        OutputView.printChessBoard(DtoMapper.generateBoardResponse(chessService.getBoard()));
    }
}
