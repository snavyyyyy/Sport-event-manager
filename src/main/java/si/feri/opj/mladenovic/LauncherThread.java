package si.feri.opj.mladenovic;

import si.feri.opj.mladenovic.match.Athlete;
import si.feri.opj.mladenovic.event.Stadium;

public class LauncherThread {
    public static void main(String[] args) {
        System.out.println("Starting the competition...");

        Stadium stadium = new Stadium();
        Thread[] athletes = new Thread[8];

        for (int i = 0; i < athletes.length; i++) {
            athletes[i] = new Thread(new Athlete(i + 1, stadium));
            System.out.println("Created athlete thread " + (i + 1));
        }

        for (Thread athlete : athletes) {
            athlete.start();
            System.out.println("Started athlete thread " + athlete.getId());
        }

        for (Thread athlete : athletes) {
            try {
                athlete.join();
                System.out.println("Joined athlete thread " + athlete.getId());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Competition finished.");
    }
}
