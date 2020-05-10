package msky.personalbudget.domain;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;
import static reactor.core.publisher.Mono.defer;
import static reactor.core.publisher.Mono.just;

@RequiredArgsConstructor
class CommandBus {

    private final CommandsHandlersProvider handlersProvider;

    public Mono<Void> execute(Command command) {
        return handlersProvider.getHandlerFor(command)
                .flatMap(handler -> handler.handle(command));
    }
}

class CommandsHandlersProvider {

    private final Map<Class, CommandHandler> handlers;

    CommandsHandlersProvider(List<CommandHandler> handlers) {
        this.handlers = handlers.stream().collect(toMap(this::getHandledClass, identity()));
    }

    private Class getHandledClass(CommandHandler handler) {
        Type commandType = handler.getClass().getGenericInterfaces()[0];
        return (Class) ((ParameterizedType) commandType).getActualTypeArguments()[0];
    }

    Mono<CommandHandler> getHandlerFor(Command command) {
        return just(command)
                .map(Command::getClass)
                .filter(handlers::containsKey)
                .map(handlers::get)
                .switchIfEmpty(
                        defer(() -> Mono.error(new RuntimeException("Unsupported command: " + command)))
                );
    }
}

interface CommandHandler<C extends Command> {
    Mono<Void> handle(C command);
}