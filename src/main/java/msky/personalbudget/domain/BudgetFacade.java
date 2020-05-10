package msky.personalbudget.domain;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@RequiredArgsConstructor
public class BudgetFacade {
    private final CommandBus commandBus;
    private final BudgetQueryHandler queryHandler;

    public Mono<Void> defineNewBudget(String userId, String budgetId, LocalDate start, LocalDate end) {
        return commandBus.execute(new DefineNewBudgetCommand(userId, budgetId, start, end));
    }

    public Mono<BudgetProjection> getBudgetById(String budgetId) {
        return queryHandler.handle(new FindBudgetByIdQuery(budgetId));
    }
}
