import java.util.ArrayList;

public class Club
{
    private ArrayList<Membership> members;
    public Club()
    {
        members = new ArrayList<>();
    }

    public void join(Membership member)
    {
        members.add(member);
    }

    public int numberOfMembers()
    {
        return members.size();
    }

    public int joinedInMonth(int month){
        int count = 0;
        if(!(month >= 1 && month <= 12)){
            System.out.println("Mes inválido!");
        }
        for(Membership m: members){
            if(m.getMonth() == month) count ++;
        }
        return count;
    }
}
