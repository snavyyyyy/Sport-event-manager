package si.feri.opj.mladenovic.event;

public class RunningTrack {
    private boolean inUse;
    private int length;
    private String stadium;

    public RunningTrack(int length, String stadium) {
        this.length = length;
        this.stadium = stadium;
        this.inUse = false;
    }

    public synchronized boolean isInUse() {
        return inUse;
    }

    public synchronized void setInUse(boolean inUse) {
        this.inUse = inUse;
    }

    public int getLength() {
        return length;
    }

    public String getStadium() {
        return stadium;
    }
}
