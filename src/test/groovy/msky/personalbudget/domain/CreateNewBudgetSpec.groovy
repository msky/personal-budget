package msky.personalbudget.domain

import spock.lang.Specification

import static msky.personalbudget.testutils.StaticTestData.*

class CreateNewBudgetSpec extends Specification {

    def budgetFacade = new BudgetFacadeConfiguration()
            .facade(new InMemoryBudgetProjectionsRepository());

    def "user can create budget for given period with custom start and end day"() {
        when: "user wants to create budget for may"
        budgetFacade.defineNewBudget(USER, BUDGET_GUID, FIRST_OF_MAY, LAST_OF_MAY).block();

        then: "user can read his budget"
        def budget = budgetFacade.getBudgetById(BUDGET_GUID).block();
        budget == new BudgetProjection(BUDGET_GUID, USER, FIRST_OF_MAY, LAST_OF_MAY)
    }
}