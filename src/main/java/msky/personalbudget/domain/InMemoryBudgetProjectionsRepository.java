package msky.personalbudget.domain;

import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

class InMemoryBudgetProjectionsRepository implements BudgetProjectionsRepository {
    private final Map<String, BudgetProjection> projections = new HashMap<>();

    @Override
    public Mono<Void> save(BudgetProjection budget) {
        projections.put(budget.id(), budget);
        return Mono.just(true).then();
    }

    @Override
    public Mono<BudgetProjection> findById(String budgetId) {
        return Mono.just(budgetId).filter(projections::containsKey).map(projections::get);
    }
}
