package org.tinroof.repository;

import org.springframework.data.repository.CrudRepository;
import org.tinroof.domain.Calendar;
import org.tinroof.domain.CalendarUser;

import java.util.List;

public interface CalendarUserRepository extends CrudRepository<CalendarUser, String>{
}
