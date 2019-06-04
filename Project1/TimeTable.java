import static java.lang.Integer.MAX_VALUE;

import java.io.*;
import java.util.*;




public class TimeTable {
    public static void main(String args[]) {
        Scanner x = new Scanner(System.in);

        int t = x.nextInt();
        for (var kk = 0; kk < t; kk++) {
            int no_course = x.nextInt();
            x.nextLine();
            Vector<myCourse> courses=new Vector();
            int no_slots;
            for (int i = 0; i < no_course; i++) {
                System.out.println("Enter the Course,Instructor Name,Priority,No. of slots");
               courses.add(new myCourse(x.next(), x.next(), x.next(),no_slots= x.nextInt()));
               for(int j=0;j<no_slots;j++){
                   System.out.println("Enter the time of slot,Day preference of slot,Time Preference of Slot");
                   courses.get(i).addtimeslot(x.nextInt(),x.next(),x.next());
               }
            }
            
            for (var i = 0; i < no_course; i++) {
                courses.get(i).print();
            }
            // t=t-1;
        }
        x.close();
    }
}




class slots {
    int time;
    String d_pref, t_pref;

    slots(int n,String a, String b) {
        time = n;
        d_pref = a;
        t_pref = b;
    }

};


class myCourse {
    String coursename, tchr_name;
    int priority;
    int no_slots;
    Vector<slots> time_slot = new Vector();

    myCourse(String a, String b, String c, int n) {

        coursename = a;
        tchr_name = b;

        if (c.compareToIgnoreCase("NIL")==0){
            priority = MAX_VALUE;
            System.out.println("sssss");
        }
        else{
            priority = Integer.parseInt(c);
        }
        no_slots = n;
       

    }
    public void addtimeslot(int n,String a,String b)
    {
        time_slot.add(new slots(n,a,b));
    }

    public void print() {
        String info;
        info = String.format("%s %s %d %d", coursename, tchr_name, priority, no_slots);
        String j = "";
        for(int i=0;i<no_slots;i++)
        {
            j+=String.format("%d %s %s", time_slot.get(i).time,time_slot.get(i).d_pref,time_slot.get(i).t_pref);
        }
        System.out.println(info + "\n");
        System.out.println(j + "\n");
    }

};