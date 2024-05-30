package si.feri.opj.mladenovic.event;

import si.feri.opj.mladenovic.AddMatchException;
import si.feri.opj.mladenovic.match.Match;

import java.util.Arrays;

public abstract class Venue {
    private String name;
    private String phoneNumber;

    Discipline discipline;
    Match[]listOfMatches;

    public Venue(){}

    public Venue(String name){
        this.name=name;
    }
    public Venue(String name, String phoneNumber,int matchSize) {
        this(name);
        this.phoneNumber=phoneNumber;
        this.listOfMatches=new Match[matchSize];
    }

    public Venue(String name, String phoneNumber) {
        this.name=name;
        this.phoneNumber=phoneNumber;
    }

    public void addMatchToTheVenue(Match match) throws AddMatchException {

            if (match.getSchedule().getHour()> 20) {
                throw new AddMatchException("No matches after 8pm.");
            } else {
                int size = getSizeOfMatches();
                listOfMatches[size] = match;
            }
    }
    public int getSizeOfMatches(){
        int size=0;
        for(Match m:listOfMatches){
            if(m!=null){
                size++;
            }
        }
        return size;
    }
    public Match[] getListOfMatches() {
        return listOfMatches;
    }
    public void removeMatchFromVenue() {
       for(int i=0; i<listOfMatches.length; i++){
          listOfMatches[i]=null;
       }
    }
    public abstract int getCapacity();

    public double getOccupancy(){
        return ((double) getSizeOfMatches())/getCapacity()*100;
    }

    public void setListOfMatches(Match[] listOfMatches) {
        this.listOfMatches = listOfMatches;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Venue{" +
                "name: " + name + '\'' +
                ", phoneNumber: " + phoneNumber + '\'' +
                ", Number of matches: " + listOfMatches.length +
                '}';
    }
}
