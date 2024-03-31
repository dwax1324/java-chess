package domain.command;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import controller.handler.StartHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StartHandlerTest {
    @Test
    @DisplayName("시작 상태에서 시작 상태로 넘어가면 예외가 발생한다")
    void next() {
        assertThatThrownBy(() -> StartHandler.getInstance().next(StartHandler.getInstance()))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("시작 상태에서 시작 상태로 넘어갈 수 없습니다");
    }
}
