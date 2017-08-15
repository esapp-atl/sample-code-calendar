package org.tinroof.repository;

import org.springframework.data.repository.CrudRepository;
import org.tinroof.domain.Calendar;

import java.util.List;

public interface CalendarRepository extends CrudRepository<Calendar, Integer>{

    List<Calendar> findByUserUserName(String userName);

}
