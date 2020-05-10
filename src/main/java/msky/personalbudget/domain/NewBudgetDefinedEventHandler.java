package msky.personalbudget.domain;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
class NewBudgetDefinedEventHandler implements EventHandler<NewBudgetDefinedEvent> {
    private final BudgetProjectionsRepository projectionsRepository;

    @Override
    public Mono<Void> handle(NewBudgetDefinedEvent event) {
        return projectionsRepository.save(new BudgetProjection(event.budgetId(),
                event.userId(), event.startDate(), event.endDate()));
    }
}
