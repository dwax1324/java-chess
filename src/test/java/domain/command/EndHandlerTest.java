package domain.command;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import controller.handler.EndHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EndHandlerTest {

    @Test
    @DisplayName("끝난 상태에서 다음 상태로 넘어가면 예외가 발생한다")
    void moveToStart() {
        assertThatThrownBy(() -> EndHandler.getInstance().next(EndHandler.getInstance()))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("끝난 상태에서 다음 상태로 넘어갈 수 없습니다");
    }

}
