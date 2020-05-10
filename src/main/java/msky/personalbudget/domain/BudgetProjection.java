package msky.personalbudget.domain;

import lombok.Value;

import java.time.LocalDate;

@Value
public class BudgetProjection {
    String id;
    String userId;
    LocalDate startDate;
    LocalDate endDate;
}
