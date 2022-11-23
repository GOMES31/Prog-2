import java.lang.reflect.Member;

public class Main {

    public static void main(String[] args) {
        Club club = new Club();
        Membership andre = new Membership("André",11,22);
        club.join(andre);
        System.out.println(club.numberOfMembers());
        club.joinedInMonth(0);
        System.out.println(club.joinedInMonth(1));
        System.out.println(club.joinedInMonth(11));

    }
}
