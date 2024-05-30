package si.feri.opj.mladenovic.match;

import si.feri.opj.mladenovic.event.RunningTrack;
import si.feri.opj.mladenovic.event.Stadium;
import si.feri.opj.mladenovic.event.Venue;

import java.time.LocalDate;
import java.util.Objects;
import java.io.Serializable;

public class Athlete implements Competition,Serializable,Runnable {
    private String name;

    private String surname;

    private static int bestTime = Integer.MAX_VALUE;
    private int athleteNumber;
    private int currentTime;
    private Stadium stadium;

    private LocalDate dateOfBirth;

    public Athlete(int athleteNumber, Stadium stadium) {
        this.athleteNumber = athleteNumber;
        this.currentTime = 0;
        this.stadium = stadium;
    }

    public Athlete(String name, String surname,int athleteNumber,LocalDate dateOfBirth) {
        this(name,surname);
        this.athleteNumber=athleteNumber;
        this.dateOfBirth=dateOfBirth;
    }

    @Override
    public void run() {
        try {
            System.out.println("Athlete " + athleteNumber + " is trying to get a track.");
            RunningTrack track = stadium.prepareRaceTrack();
            if (track != null) {
                System.out.println("Athlete " + athleteNumber + " is running on track in " + track.getStadium());

                int runningTime = (int) (Math.random() * 1000) + 5000;
                Thread.sleep(runningTime);

                currentTime = runningTime;
                synchronized (Athlete.class) {
                    if (currentTime < bestTime) {
                        bestTime = currentTime;
                    }
                }

                System.out.println("Athlete " + athleteNumber + " finished in " + currentTime + " ms. Best time: " + bestTime + " ms");

                track.setInUse(false);
            } else {
                System.out.println("No available track for athlete " + athleteNumber);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getCurrentTime() {
        return currentTime;
    }
    Match []listOfPersonalMatches = new Match[5] ;

    public Match[] getListOfPersonalMatches() {
        return listOfPersonalMatches;
    }

    public void setListOfPersonalMatches(Match[] listOfPersonalMatches) {
        this.listOfPersonalMatches = listOfPersonalMatches;
    }

    public Athlete(){}
    public Athlete(String name,String surname){
        this.name=name;
        this.surname=surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAthleteNumber() {
        return athleteNumber;
    }

    public void setAthleteNumber(int athleteNumber) {
        this.athleteNumber = athleteNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Athlete athlete = (Athlete) o;
        return athleteNumber == athlete.athleteNumber && Objects.equals(name, athlete.name) && Objects.equals(surname, athlete.surname) && Objects.equals(dateOfBirth, athlete.dateOfBirth);
    }

    @Override
    public String toString() {
        return "Athlete{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", athleteNumber=" + athleteNumber +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }

    @Override
    public boolean checkValidity(Venue venue) {
        Match []arrayOfMatches= venue.getListOfMatches();
        for(Match match:arrayOfMatches){
            if (match != null && match.athleteExist(this.getSurname())){
                addMatch(match);
            }
        }
        return false;
    }
    public void addMatch(Match match){
        int count=0;
        for (int i=0; i<listOfPersonalMatches.length; i++){
            if(listOfPersonalMatches[i] != null){
             count++;
            }
        }
        listOfPersonalMatches[count]=match;
    }

}
