package si.feri.opj.mladenovic.event;

import si.feri.opj.mladenovic.match.Athlete;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Event {
    private String name;
    private LocalDateTime schedule;
    private boolean canceled;

    private ArrayList<Athlete> participants;

    public Event(String name) {
        this.name = name;
        this.participants = new ArrayList<>();
    }

    public ArrayList<Athlete> getParticipants() {
        return participants;
    }
    public Event(){}
    public Event(String name, LocalDateTime schedule, boolean canceled) {
        this.name=name;
        this.schedule=schedule;
        this.canceled=canceled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getSchedule() {
        return schedule;
    }

    public void setSchedule(LocalDateTime schedule) {
        this.schedule = schedule;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }
    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", schedule=" + schedule +
                ", canceled=" + canceled +
                '}';
    }
}
