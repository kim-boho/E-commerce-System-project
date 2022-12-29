package com.groupad.backend.bl;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;
import com.groupad.backend.model.VisitEvent;

public class AdminBL {
    /*
     * This class is responsible for handling all the business logic related to
     * visit events
     * 
     * @Michelangelo-Granato
     */

    public List<VisitEvent> getMonthlySellReport(List<VisitEvent> events) {

        // filter out events that aren't from this month
        return events.stream()
                .filter(e -> e.getEventDate().getYear() == getCurrentYear())
                .filter(e -> e.getEventDate().getMonth().equals(getCurrentMonth()))
                .collect(Collectors.toList());
    }

    private int getCurrentYear() {
        return LocalDate.now().getYear();
    }

    private Month getCurrentMonth() {
        return LocalDate.now().getMonth();
    }
}
