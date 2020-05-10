package msky.personalbudget.domain;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
class DefineNewBudgetCommandHandler implements CommandHandler<DefineNewBudgetCommand> {

    private final EventsPublisher events;

    @Override
    public Mono<Void> handle(DefineNewBudgetCommand command) {
        return events.publish(new NewBudgetDefinedEvent(command.userId(),
                command.budgetGUID(),
                command.startDate(),
                command.endDate()));
    }
}
