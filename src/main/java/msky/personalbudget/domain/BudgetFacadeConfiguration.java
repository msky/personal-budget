package msky.personalbudget.domain;

public class BudgetFacadeConfiguration {

    public BudgetFacade facade(BudgetProjectionsRepository projectionsRepository) {
        EventsConfiguration eventsConfig = new EventsConfiguration(projectionsRepository);
        CommandBusConfiguration commandsConfig = new CommandBusConfiguration();
        QueryHandlerConfiguration queryConfig = new QueryHandlerConfiguration(projectionsRepository);
        return new BudgetFacade(commandsConfig.commandBus(eventsConfig),
                queryConfig.queryHandler());
    }
}

