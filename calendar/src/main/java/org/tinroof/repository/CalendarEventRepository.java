package org.tinroof.repository;

import org.springframework.data.repository.CrudRepository;
import org.tinroof.domain.Calendar;
import org.tinroof.domain.CalendarEvent;

import java.util.List;

public interface CalendarEventRepository extends CrudRepository<CalendarEvent, Integer>{

}
