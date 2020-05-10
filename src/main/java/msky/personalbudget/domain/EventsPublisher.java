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
class EventsPublisher {
    private final EventsBus eventsBus;

    Mono<Void> publish(Event event) {
        return eventsBus.dispatch(event);
    }
}

@RequiredArgsConstructor
class EventsBus {

    private final EventsHandlerProvider handlersProvider;

    Mono<Void> dispatch(Event event) {
        return handlersProvider.getHandlerFor(event).flatMap(handler -> handler.handle(event));
    }
}

interface EventHandler<E> {
    Mono<Void> handle(E event);
}

class EventsHandlerProvider {
    private final Map<Class, EventHandler> handlers;

    EventsHandlerProvider(List<EventHandler> handlers) {
        this.handlers = handlers.stream().collect(toMap(this::getHandledClass, identity()));
    }

    private Class getHandledClass(EventHandler handler) {
        Type eventType = handler.getClass().getGenericInterfaces()[0];
        return (Class) ((ParameterizedType) eventType).getActualTypeArguments()[0];
    }

    Mono<EventHandler> getHandlerFor(Event event) {
        return just(event)
                .map(Event::getClass)
                .filter(handlers::containsKey)
                .map(handlers::get)
                .switchIfEmpty(
                        defer(() -> Mono.error(new RuntimeException("Unsupported event: " + event)))
                );
    }
}
