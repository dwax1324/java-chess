package controller.handler;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;

public enum HandlerMapper {

    START(Pattern.compile("start"), new StartHandler()), STATUS(Pattern.compile("status"), new StatusHandler()), MOVE(
            Pattern.compile("move ([a-h][0-8]) ([a-h][0-8])"), new MoveHandler()), END(Pattern.compile("end"),
            new EndHandler());
    private final Pattern pattern;
    private final CommandHandler commandHandler;

    HandlerMapper(final Pattern pattern, final CommandHandler commandHandler) {
        this.pattern = pattern;
        this.commandHandler = commandHandler;
    }


    public static CommandHandler findBy(final String rawCommand) {
        return Arrays.stream(values())
                .filter(value -> value.pattern.matcher(rawCommand).matches())
                .map(value -> value.commandHandler)
                .findAny()
                .orElseThrow(() -> new NoSuchElementException(String.format("찾으려는 커맨드: %s,가 존재하지 않습니다.", rawCommand)));
    }
}

