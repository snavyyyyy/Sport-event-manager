package si.feri.opj.mladenovic.event;

import java.time.LocalDateTime;

public class Training extends Event{
    private String trainerName;
    public Training(){}
    public Training(String name, LocalDateTime schedule, String trainerName, boolean canceled){
        super(name,schedule,canceled);
        this.trainerName=trainerName;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    @Override
    public String toString() {
        return super.toString() +"Training{" +
                "trainerName='" + trainerName + '\'' +
                '}';
    }
}
