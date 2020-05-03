package msky.personalbudget.domain;

import reactor.core.publisher.Mono;

public class BudgetCommandBus {

    public Mono<Void> execute(Command defineNewBudgetCommand) {
        return Mono.empty();
    }
}
