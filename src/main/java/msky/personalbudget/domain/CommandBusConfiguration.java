package msky.personalbudget.domain;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
class CommandBusConfiguration {

    CommandBus commandBus(EventsConfiguration eventsConfig) {
        return new CommandBus(new CommandsHandlersProvider(handlers(eventsConfig)));
    }

    private List<CommandHandler> handlers(EventsConfiguration eventsConfig) {
        return List.of(new DefineNewBudgetCommandHandler(eventsConfig.publisher()));
    }
}
