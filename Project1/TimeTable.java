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
            
            
            // Comparator<myCourse> comparator = new sortByPriority();
            courses.sort(new sortByPriority());
            Vector<myCourse> saturday=new Vector();
            Vector<week> course_day=new Vector();
            course_day.add(new week("Monday",1));
            course_day.add(new week("Tuesday",2));
            course_day.add(new week("Wednesday",3));
            course_day.add(new week("Thursday",4));
            course_day.add(new week("Friday",5));

            int flag=1;

            for (int i = 0; i < no_course; i++) {
                courses.get(i).print();
            }
            while(flag==1){
                for(int i=0;i<no_course;i++)
                {
                    week.sort(new sortByLoad());

                }
            }

        }
        x.close();
    }
}

class sortByPriority implements Comparator<myCourse>
{
    // Used for sorting in ascending order of priority
    public int compare(myCourse a, myCourse b)
    {
        return (a.priority - b.priority);
    }
}
class sortByLoad implements Comparator<week>
{
    //sort week in ascending order of load
    public int compare(week a,week b){
        return (a.load-b.load);
    }
};




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
    myCourse(){

    }

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
        System.out.println(info);
        System.out.println(j );
    }

};

//
// i am working on starting hours if starting hours are occupied or not
class week{
    String name;
    int day_no;
    Vector<myCourse> times=new Vector();
    int load;
    week(String week_name,int n){
        name=week_name;
        day_no=n;
       
        for(int i=0;i<7;i++)
        {
            times.addElement(null);
        }
        load=0;

    }
    public int slot_time(String a){
        switch (a)
        {
            case "0900":return 0;
            case "1000":return 1;
            case "1115":return 2;
            case "1215":return 3;
            case "0300":return 4;
            case "0400":return 5;
            case "0500":return 6;
        }
        return -1;
        
    }

};