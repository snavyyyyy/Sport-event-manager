package si.feri.opj.mladenovic;

import si.feri.opj.mladenovic.event.Event;
import si.feri.opj.mladenovic.event.Hall;
import si.feri.opj.mladenovic.event.Schedule;
import si.feri.opj.mladenovic.event.Venue;
import si.feri.opj.mladenovic.match.Athlete;
import si.feri.opj.mladenovic.match.Match;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.*;

public class Launcher {
   /* private static final Scanner scanner = new Scanner(System.in);
    public static Athlete[] listOfAthletes(Match match, int number) throws AddAthleteException {
        for (int i = 0; i < number; i++) {
            System.out.printf("Enter data for %d athlete:%n",i+1);
            String name = scanner.next();
            String surname = scanner.next();
            int athleteNumber = scanner.nextInt();
            LocalDate date = LocalDate.parse(scanner.next());
            Athlete athlete = new Athlete(name, surname, athleteNumber, date);
            match.addAthleteToTheMatch(athlete);
        }
        return match.getAthleteList();
    }
    public static void printList(Athlete []athleteList,int number){
        for(int i=0; i< number; i++){
            System.out.println(athleteList[i]);
        }
    }
    public static Match[] listOfMatches(Venue venue,int size) throws AddMatchException {
        for(int i=0; i<size; i++){
            System.out.printf("Enter data for %d match:%n",i+1);
            System.out.println("Enter name of match:");
            String name=scanner.next();
            System.out.println("Enter if match is canceled:");
            boolean canceled=scanner.nextBoolean();
            LocalDateTime schedule = null;
            Match match=new Match(name,schedule,canceled);
            venue.addMatchToTheVenue(match);
        }
       return venue.getListOfMatches();
    }
    public static void printList(Match []matchList,int number){
        for(int i=0; i< number; i++){
            if(matchList[i]!=null)
            System.out.println(matchList[i]);
        }
    }

    */

    public static void main(String[] args) throws AddAthleteException, AddMatchException,IOException {
       /* String matchName = "Basketball match";
        LocalDateTime matchDate = (LocalDateTime.of(2024, 5, 5, 18, 30));
        Boolean isCancelled = false;
        Match match = new Match(matchName, matchDate,isCancelled);

        Athlete[] athleteList = listOfAthletes(match, 3);
        printList(athleteList, match.getNumberOfAthletesInMatch());

        System.out.println("-------REMOVE:----------");
        match.removeAthleteFromMatch(athleteList[0]);
        athleteList= match.getAthleteList();
        printList(athleteList, match.getNumberOfAthletesInMatch());

        System.out.println("---------REMOVE WITH NUMBER:----------");
        boolean athleteRemoved= match.removeAthleteFromMatch(3);
        if(!athleteRemoved) {
            throw new RuntimeException("Athlete with that number does not exist.");
       }
        athleteList = match.getAthleteList();
        printList(athleteList, match.getNumberOfAthletesInMatch());

        System.out.println("------EXIST BY SURNAME-------- ");
        String surname="Mladenovic";
        boolean exists=match.athleteExist(surname);
        if(exists){

            System.out.printf("Athlete with surname %s exists. %n",surname);
        }
        else{
            System.out.printf("Athlete with surname %s does not exist.%n",surname);
        }


       String venueName="venue";
        String venuePhoneNumber="000-000";
        Venue venue=new Hall(2,venueName,venuePhoneNumber,3);
        Match []matches=listOfMatches(venue,3);
        System.out.println("-------OUTPUT:----------");
        printList(matches,3);
        System.out.println("-------REMOVE:----------");
        venue.removeMatchFromVenue();
        matches=venue.getListOfMatches();
        printList(matches,3);
        System.out.println("--------CAPACITY:----------");
        System.out.println(venue.getCapacity());
        System.out.println("---------OCCUPANCY:---------");
        System.out.println(venue.getOccupancy());

        try {
            venueName = "venue";
             venuePhoneNumber = "000-000";
             venue = new Hall(2, venueName, venuePhoneNumber, 3);
            LocalDateTime schedule = (LocalDateTime.of(2024,05,05,19,00,00));
            match = new Match("match1", schedule, false);
            Athlete athlete1 = new Athlete("Visnja","Mladenovic", 12, LocalDate.of(2009,05,01));
            Athlete athlete2 = new Athlete("Ilija","Neskovic");
            match.addAthleteToTheMatch(athlete1);
            venue.addMatchToTheVenue(match);
            match.checkValidity(venue);
            athlete1.checkValidity(venue);
            Match[] athleteMatches = athlete1.getListOfPersonalMatches();
            for(Match match1: athleteMatches) {
                if (match1 != null && match1.equals(match)) {
                    System.out.println(true);
                }
            }
            LocalDateTime schedule1 =(LocalDateTime.of(2024,05,05,21,00,00));
            Match match2 = new Match("visnja", schedule1, true);
            venue.addMatchToTheVenue(match2);
            match2.checkValidity(venue);

            System.out.println(Arrays.toString(athlete1.getListOfPersonalMatches()));
        } catch (AddMatchException e) {
          e.printStackTrace();
        } catch (AddAthleteException e) {
            e.printStackTrace();
        }

        */

        // ITSupportGUI itSupportGUI = new ITSupportGUI();
        Athlete athlete = new Athlete("Visnja", "Mladenovic", 15, LocalDate.of(2007, 01, 23));

        FileOutputStream fileOutputStream = new FileOutputStream("UserInfo.ser");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(athlete);
        objectOutputStream.close();
        fileOutputStream.close();

        System.out.println("Object info saved! :)");

        Athlete loadedAthlete = loadObject();
        if (loadedAthlete != null) {
            System.out.println("Loaded object: " + loadedAthlete);
        } else {
            System.out.println("No object was loaded.");
        }
    }


    private static Athlete loadObject(){
        try (FileInputStream fileInputStream = new FileInputStream("UserInfo.ser");
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            return (Athlete) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}