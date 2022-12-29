/**
 * 
 */
package com.groupad.backend.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.groupad.backend.bl.AdminBL;
import com.groupad.backend.enums.EventType;
import com.groupad.backend.model.VisitEvent;
import com.groupad.backend.repository.VisitEventRepository;

@RestController
public class AdminController {

    @Autowired
    private final VisitEventRepository repository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    private AdminBL adminBL;

    public AdminController(VisitEventRepository repository) {
        this.repository = repository;
        this.adminBL = new AdminBL();
    }

    @GetMapping("/admin/monthly-purchases")
    public List<VisitEvent> getMonthlyReport() {
        // get all purchases
        List<VisitEvent> events = repository.findByEventType(EventType.PURCHASE.toString());
        // consolidate list of purchases for the month by item
        return adminBL.getMonthlySellReport(events);
    }

    @GetMapping("/admin/website-usage")
    public List<VisitEvent> getUsageReport() {
        return repository.findAll();
    }

    @CrossOrigin
    @PostMapping("/admin/visitEvent")
    public VisitEvent postVisitEvent(@RequestBody VisitEvent event) {
        // get client ip for log
        String remoteAddress = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest().getRemoteAddr();
        // get date for log
        LocalDate today = LocalDate.now();

        event.setEventDate(today);
        event.setIP(remoteAddress);

        return repository.save(event);
    }

}