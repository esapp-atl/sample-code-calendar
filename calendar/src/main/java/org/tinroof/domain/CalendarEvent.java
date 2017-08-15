package org.tinroof.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
public class CalendarEvent {

//calendar, title, event date and time, location, attendee list, reminder time, and whether the reminder has been sent).

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String title;

    private LocalDate eventDate;

    private LocalTime eventTime;

    private String location;

    @ManyToMany(targetEntity = CalendarUser.class, fetch = FetchType.EAGER)
    private List<CalendarUser> attendees;

    private LocalDateTime reminderDateTime;

    private boolean reminderSent;

    public CalendarEvent() {}

    public CalendarEvent(Integer id, Calendar calendar, String title, LocalDate eventDate, LocalTime eventTime, String location, List<CalendarUser> attendees, LocalDateTime reminderDateTime, boolean reminderSent) {
        this.id = id;
        this.title = title;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.location = location;
        this.attendees = attendees;
        this.reminderDateTime = reminderDateTime;
        this.reminderSent = reminderSent;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public LocalTime getEventTime() {
        return eventTime;
    }

    public void setEventTime(LocalTime eventTime) {
        this.eventTime = eventTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<CalendarUser> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<CalendarUser> attendees) {
        this.attendees = attendees;
    }

    public LocalDateTime getReminderDateTime() {
        return reminderDateTime;
    }

    public void setReminderDateTime(LocalDateTime reminderDateTime) {
        this.reminderDateTime = reminderDateTime;
    }

    public boolean isReminderSent() {
        return reminderSent;
    }

    public void setReminderSent(boolean reminderSent) {
        this.reminderSent = reminderSent;
    }

    @Override
    public String toString() {
        return "CalendarEvent{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", eventDate=" + eventDate +
                ", eventTime=" + eventTime +
                ", location='" + location + '\'' +
                ", attendees=" + attendees +
                ", reminder=" + reminderDateTime +
                ", reminderSent=" + reminderSent +
                '}';
    }


}
