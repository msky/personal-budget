package msky.personalbudget.domain;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
class EventsConfiguration {

    private final BudgetProjectionsRepository projectionsRepository;

    public EventsPublisher publisher() {
        return new EventsPublisher(new EventsBus(new EventsHandlerProvider(eventsHandlers())));
    }

    private List<EventHandler> eventsHandlers() {
        return List.of(new NewBudgetDefinedEventHandler(projectionsRepository));
    }
}
