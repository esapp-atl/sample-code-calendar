package org.tinroof.domain;

import javax.persistence.*;
import java.util.*;

@Entity
public class Calendar {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="CALENDAR_ID")
    private Integer id;

    @ManyToOne
    private CalendarUser user;

    private String calendarName;

    private String description;

    @OneToMany(targetEntity = CalendarEvent.class, fetch = FetchType.EAGER)
    private List<CalendarEvent> events;

    public Calendar() {}

    public Calendar(CalendarUser user, String calendarName, String description) {
        this.user = user;
        this.calendarName = calendarName;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CalendarUser getUser() {
        return user;
    }

    public void setUser(CalendarUser user) {
        this.user = user;
    }

    public String getCalendarName() {
        return calendarName;
    }

    public void setCalendarName(String name) {
        this.calendarName = calendarName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<CalendarEvent> getEvents() {
        return events;
    }

    public void setEvents(List<CalendarEvent> events) {
        this.events = events;
    }

    public void addEvent(CalendarEvent event) {
        if (this.events == null) {
            this.events = new ArrayList<CalendarEvent>();
        }
        this.events.add(event);
    }

    @Override
    public String toString() {
        return "Calendar{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", calendarName='" + calendarName + '\'' +
                ", description='" + description + '\'' +
                ", events=" + events +
                '}';
    }
}
