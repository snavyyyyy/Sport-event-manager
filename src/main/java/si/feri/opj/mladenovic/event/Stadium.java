package si.feri.opj.mladenovic.event;

import java.util.ArrayList;
import java.util.List;

public class Stadium {
    private List<RunningTrack> runningTracks;

    public Stadium() {
        this.runningTracks = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            runningTracks.add(new RunningTrack(100, "Stadium " + i));
        }
    }

    public synchronized RunningTrack prepareRaceTrack() {
        for (RunningTrack track : runningTracks) {
            if (!track.isInUse()) {
                track.setInUse(true);
                return track;
            }
        }
        return null;
    }
}
