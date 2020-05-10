package msky.personalbudget.domain;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
class QueryHandlerConfiguration {

    private final BudgetProjectionsRepository projectionsRepository;

    BudgetQueryHandler queryHandler() {
        return new BudgetQueryHandler(projectionsRepository);
    }
}

@RequiredArgsConstructor
class BudgetQueryHandler {

    private final BudgetProjectionsRepository repository;

    public Mono<BudgetProjection> handle(FindBudgetByIdQuery query) {
        return repository.findById(query.budgetId());
    }
}

