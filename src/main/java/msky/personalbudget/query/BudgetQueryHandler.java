package msky.personalbudget.query;

import reactor.core.publisher.Mono;

public class BudgetQueryHandler {
    public Mono<BudgetProjection> handle(FindBudgetByIdQuery findBudgetByIdQuery) {
        return Mono.empty();
    }
}
