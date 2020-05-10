package msky.personalbudget.domain;

import reactor.core.publisher.Mono;

interface BudgetProjectionsRepository {
    Mono<Void> save(BudgetProjection budget);

    Mono<BudgetProjection> findById(String budgetId);
}
