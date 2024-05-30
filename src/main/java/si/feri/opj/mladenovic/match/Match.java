package si.feri.opj.mladenovic.match;

import si.feri.opj.mladenovic.AddAthleteException;
import  si.feri.opj.mladenovic.event.Event;
import si.feri.opj.mladenovic.event.Venue;

import java.time.LocalDateTime;

public class Match extends Event implements Competition {
    private Athlete[] athleteList;
    public Match(String name, LocalDateTime schedule, Boolean cancelled){
        super(name,schedule,cancelled);
        this.athleteList = new Athlete[10];
    }

    public Boolean canCompete(Athlete athlete) {
        int currentYear = LocalDateTime.now().getYear();
        int yearOfBirth = athlete.getDateOfBirth().getYear();
        int yearsOfAthlete = currentYear - yearOfBirth;

        return (yearsOfAthlete >= 14 && yearsOfAthlete <= 18);
    }
    public void addAthleteToTheMatch(Athlete athlete) throws AddAthleteException {
        if(!canCompete(athlete)) {
            throw new AddAthleteException("Athlete is not allowed to compete.");
        }
        int size = getNumberOfAthletesInMatch();
        athleteList[size] = athlete;
    }
    public Athlete[] getAthleteList() {
        return athleteList;
    }

    public void setAthleteList(Athlete[] athleteList) {
        this.athleteList = athleteList;
    }
    public void removeAthleteFromMatch(Athlete athlete) {
        for (Athlete value : athleteList) {
            if (value != null && !value.equals(athlete)) {
                athlete = null;
            }
        }
    }

    public boolean removeAthleteFromMatch(int athleteNumber) {
        for (Athlete athlete : athleteList) {
            if (athlete != null && athlete.getAthleteNumber() == athleteNumber) {
                removeAthleteFromMatch(athlete);
                return true;
            }
        }
        return false;
    }
    public int getNumberOfAthletesInMatch() {
        int number = 0;
        for (Athlete athlete : athleteList) {
            if (athlete != null) {
                number++;
            }
        }
        return number;
    }

    public boolean athleteExist(String lastName) {
        for (Athlete athlete : athleteList) {
            if (athlete != null && athlete.getSurname().equals(lastName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void run() {

    }

    @Override
    public boolean checkValidity(Venue venue) {
        for (Match match: venue.getListOfMatches()){
            if (match!=null && match.equals(this)){
                System.out.println("The match " + match.getName() + " can take place");
                break;
            }
            else{
                System.out.println("The match " + match.getName() + " has not been added to the venue");
                break;
            }
        }
        return false;
    }
}
