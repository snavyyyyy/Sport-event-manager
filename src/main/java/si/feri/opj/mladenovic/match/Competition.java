package si.feri.opj.mladenovic.match;

import si.feri.opj.mladenovic.event.Venue;

public interface Competition {
    void run();

    public boolean checkValidity(Venue venue);
}
