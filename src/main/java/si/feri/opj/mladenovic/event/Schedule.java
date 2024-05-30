package si.feri.opj.mladenovic.event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class Schedule {

    public  Scanner scanner=new Scanner(System.in);
    private LocalDateTime dateTime;

    public Schedule(){}

    public Schedule(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
    public void inputDateTime(){
        System.out.println("Enter date and time for match(yyyy-MM-dd HH:mm:ss):");
        String inputDateTimeString= scanner.nextLine();
        LocalDateTime inputDateTime=LocalDateTime.parse(inputDateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.dateTime=inputDateTime;
    }
    @Override
    public String toString() {
        return "Schedule{" +
                "dateTime=" + dateTime +
                '}';
    }
}
