/**
 * 
 */
package com.groupad.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.groupad.backend.model.VisitEvent;

/**
 * @author Michelangelo Granato
 *
 */
public interface VisitEventRepository extends JpaRepository<VisitEvent, String> {
    List<VisitEvent> findByEventType(String eventType);
}