package si.feri.opj.mladenovic.event;

public class ConcreteVenue extends Venue{

    public ConcreteVenue(String name, String phoneNumber, int matchSize) {
        super(name, phoneNumber, matchSize);
    }

    @Override
    public int getCapacity() {
        return 0;
    }
}
