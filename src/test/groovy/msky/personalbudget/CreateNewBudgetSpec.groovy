package msky.personalbudget

import msky.personalbudget.domain.BudgetCommandBus
import msky.personalbudget.domain.DefineNewBudgetCommand
import msky.personalbudget.query.BudgetQueryHandler
import msky.personalbudget.query.FindBudgetByIdQuery
import spock.lang.Specification

import static msky.personalbudget.testutils.StaticTestData.*

class CreateNewBudgetSpec extends Specification {

    def commandBus = new BudgetCommandBus()
    def queryHandler = new BudgetQueryHandler()

    def "user can create budget for given period with custom start and end day"() {
        given: "user wants to create budget for may"
        def defineNewBudgetCommand = new DefineNewBudgetCommand(USER,
                BUDGET_GUID, FIRST_OF_MAY, LAST_OF_MAY)

        when: "user sends request to create budget for may"
        commandBus.execute(defineNewBudgetCommand).block()

        then: "user can read his budget"
        def budget = queryHandler.handle(new FindBudgetByIdQuery(BUDGET_GUID)).block()
        budget.id == BUDGET_GUID
    }
}