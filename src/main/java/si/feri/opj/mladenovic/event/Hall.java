package si.feri.opj.mladenovic.event;

public class Hall extends Venue{
    private int numberOfAditionalHalls;

    public Hall(){}
    @Override
    public int getCapacity() {
        return numberOfAditionalHalls+super.listOfMatches.length;
    }
    public Hall(int numberOfAditionalHalls,String venueName, String venuePhoneNumber,int matchSize){
        super(venueName,venuePhoneNumber,matchSize);
        this.numberOfAditionalHalls=numberOfAditionalHalls;
    }

    @Override
    public String toString() {
        return super.toString()+"Hall{" +
                "numberOfAditionalHalls=" + numberOfAditionalHalls + '}';
    }
}
