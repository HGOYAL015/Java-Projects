import static java.lang.Integer.MAX_VALUE;

// import java.io.*;
import java.util.*;

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
    public int get_slot(){
        return this.no_slots;
    }

};

public class TimeTable {
    public static void main(String args[]) {
        Scanner x = new Scanner(System.in);

        int t = x.nextInt();
        for (var kk = 0; kk < t; kk++) {
            int no_course = x.nextInt();
            x.nextLine();
            Vector<myCourse> courses=new Vector();
            int no_slo,max_slot=0;
            for (int i = 0; i < no_course; i++) {
                // System.out.println("Enter the Course,Instructor Name,Priority,No. of slots");
               courses.add(new myCourse(x.next(), x.next(), x.next(),no_slo= x.nextInt()));
               if(max_slot<no_slo)
               max_slot=no_slo;
               for(int j=0;j<no_slo;j++){
                //    System.out.println("Enter the time of slot,Day preference of slot,Time Preference of Slot");
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


            for (int i = 0; i < no_course; i++) {
                courses.get(i).print();
            }
            // myCourse temp=new myCourse();
            for(int s=0;s<max_slot;s++)
            {
                for(int i=0;i<no_course;i++)
                {
                    int flag=1;

                   
                    // course_day.sort(new sortByLoad());
                    //now sorting week days acc.to priority
                    
                    int l=courses.get(i).get_slot();
                    if(s<l)
                    {
                        slots temp1=courses.get(i).time_slot.get(s);
                        String day=courses.get(i).time_slot.get(s).d_pref;
                        int day_length=day.length();
                        String Time_pref=temp1.t_pref;

                        // System.out.println(day_length);
                        // Vector<week> temporary=new Vector();
                        int pri=1;

                        while(flag==1 && pri==1)
                        {
                            int time_start=0;

                            for(int j=0;j<day_length&&flag==1;j++)
                            {
                                String cc=day.substring(j,j+1);
                                int h=Integer.parseInt(cc);
                                // temporary.add(course_day.get(h-1));
                                week temp2=course_day.get(h-1);
                                String kkk=Time_pref.substring(time_start,time_start+4);
                                int time_no=temp2.slot_time(kkk);
                                if(temp1.time==1)
                                {
                                   
                                    if(temp2.times.get(time_no)==null)
                                    {
                                        flag=0;
                                        temp2.set_time(courses.get(i),time_no);
                                        temp2.update_load();

                                    }


                                }
                                else
                                {
                                    if(temp1.time==2)
                                    {
                                        
                                        System.out.println("Time slot is 2");
                                        if(time_no==0||time_no==2||time_no==4||time_no==5)
                                        {
                                            if(temp2.times.get(time_no)==null&&temp2.times.get(time_no+1)==null)
                                            {
                                                flag=0;
                                                temp2.set_time(courses.get(i),time_no);
                                                temp2.set_time(courses.get(i),time_no+1); // it has to be changed later
                                                temp2.update_load();

                                            }
                                        }
                                        // flag=0;
                                    }
                                    else
                                    {
                                        if(temp1.time==3)
                                        {
                                        System.out.println("Time slot is 3");
                                        if(time_no==4)
                                        {
                                            if(temp2.times.get(time_no)==null&&temp2.times.get(time_no+1)==null&&temp2.times.get(time_no+2)==null)
                                            {
                                                flag=0;
                                                temp2.set_time(courses.get(i),time_no);
                                                temp2.set_time(courses.get(i),time_no+1); // it has to be changed later
                                                temp2.set_time(courses.get(i),time_no+2); // it has to be changed later
                                                temp2.update_load();


                                            }
                                        }
                                        // flag=0;

                                        }
                                        else
                                        {
                                            System.out.println("Time slot is greater than 3");
                                            saturday.add(courses.get(i));
                                            flag=0;
                                        }
                                    }
                                }                          
                            }



                            //yet to add other days
                            Vector<week> temporary=new Vector();
                            for(int j=0;j<5&&flag==1;j++)
                            {
                                String xxx=Integer.toString(j+1);
                                if(Time_pref.indexOf(xxx)==-1)
                                temporary.add(course_day.get(j));

                            }
                            temporary.sort(new sortByLoad());

                            for(int j=0;j<temporary.size()&&flag==1;j++)
                            {
                                week temp2=temporary.get(j);
                                String kkk=Time_pref.substring(time_start,time_start+4);
                                int time_no=temp2.slot_time(kkk);
                                if(temp1.time==1)
                                {
                                   
                                    if(temp2.times.get(time_no)==null)
                                    {
                                        flag=0;
                                        temp2.set_time(courses.get(i),time_no);
                                        temp2.update_load();

                                    }


                                }
                                else
                                {
                                    if(temp1.time==2)
                                    {
                                        
                                        System.out.println("Time slot is 2");
                                        if(time_no==0||time_no==2||time_no==4||time_no==5)
                                        {
                                            if(temp2.times.get(time_no)==null&&temp2.times.get(time_no+1)==null)
                                            {
                                                flag=0;
                                                temp2.set_time(courses.get(i),time_no);
                                                temp2.set_time(courses.get(i),time_no+1); // it has to be changed later
                                                temp2.update_load();

                                            }
                                        }
                                        // flag=0;
                                    }
                                    else
                                    {
                                        if(temp1.time==3)
                                        {
                                        System.out.println("Time slot is 3");
                                        if(time_no==4)
                                        {
                                            if(temp2.times.get(time_no)==null&&temp2.times.get(time_no+1)==null&&temp2.times.get(time_no+2)==null)
                                            {
                                                flag=0;
                                                temp2.set_time(courses.get(i),time_no);
                                                temp2.set_time(courses.get(i),time_no+1); // it has to be changed later
                                                temp2.set_time(courses.get(i),time_no+2); // it has to be changed later
                                                temp2.update_load();


                                            }
                                        }
                                        // flag=0;

                                        }
                                        else
                                        {
                                            System.out.println("Time slot is greater than 3");
                                            saturday.add(courses.get(i));
                                            flag=0;
                                        }
                                    }
                                }
                            }

                            
                            time_start+=4;
                            if(time_start>=Time_pref.length())
                            pri=0;

                        }


                        if(flag==1)
                        {
                            for(int i1=0;i1<7&&flag==1;i1++)
                            {
                                // int time_start=0;
    
                                for(int j=0;j<day_length&&flag==1;j++)
                                {
                                    String cc=day.substring(j,j+1);
                                    int h=Integer.parseInt(cc);
                                    // temporary.add(course_day.get(h-1));
                                    week temp2=course_day.get(h-1);
                                    // String kkk=Time_pref.substring(time_start,time_start+4);
                                    // int time_no=temp2.slot_time(kkk);
                                    if(temp1.time==1)
                                    {
                                       
                                        if(temp2.times.get(i1)==null)
                                        {
                                            flag=0;
                                            temp2.set_time(courses.get(i),i1);
                                            temp2.update_load();
    
                                        }
    
    
                                    }
                                    else
                                    {
                                        if(temp1.time==2)
                                        {
                                            
                                            System.out.println("Time slot is 2");
                                            if(i1==0||i1==2||i1==4||i1==5)
                                            {
                                                if(temp2.times.get(i1)==null&&temp2.times.get(i1+1)==null)
                                                {
                                                    flag=0;
                                                    temp2.set_time(courses.get(i),i1);
                                                    temp2.set_time(courses.get(i),i1+1); // it has to be changed later
                                                    temp2.update_load();
    
                                                }
                                            }
                                            // flag=0;
                                        }
                                        else
                                        {
                                            if(temp1.time==3)
                                            {
                                            System.out.println("Time slot is 3");
                                            if(i1==4)
                                            {
                                                if(temp2.times.get(i1)==null&&temp2.times.get(i1+1)==null&&temp2.times.get(i1+2)==null)
                                                {
                                                    flag=0;
                                                    temp2.set_time(courses.get(i),i1);
                                                    temp2.set_time(courses.get(i),i1+1); // it has to be changed later
                                                    temp2.set_time(courses.get(i),i1+2); // it has to be changed later
                                                    temp2.update_load();
    
    
                                                }
                                            }
                                            // flag=0;
    
                                            }
                                            else
                                            {
                                                System.out.println("Time slot is greater than 3");
                                                saturday.add(courses.get(i));
                                                flag=0;
                                            }
                                        }
                                    }                          
                                }
    
    
                                
                                //yet to add other days
                                Vector<week> temporary1=new Vector();
                                for(int j1=0;j1<5&&flag==1;j1++)
                                {
                                    String xxx1=Integer.toString(j1+1);
                                    if(Time_pref.indexOf(xxx1)==-1)
                                    temporary1.add(course_day.get(j1));
    
                                }
                                temporary1.sort(new sortByLoad());
    
                                for(int j=0;j<temporary1.size()&&flag==1;j++)
                                {
                                    week temp2=temporary1.get(j);
                                    
                                    if(temp1.time==1)
                                    {
                                       
                                        if(temp2.times.get(i1)==null)
                                        {
                                            flag=0;
                                            temp2.set_time(courses.get(i),i1);
                                            temp2.update_load();
    
                                        }
    
    
                                    }
                                    else
                                    {
                                        if(temp1.time==2)
                                        {
                                            
                                            System.out.println("Time slot is 2");
                                            if(i1==0||i1==2||i1==4||i1==5)
                                            {
                                                if(temp2.times.get(i1)==null&&temp2.times.get(i1+1)==null)
                                                {
                                                    flag=0;
                                                    temp2.set_time(courses.get(i),i1);
                                                    temp2.set_time(courses.get(i),i1+1); // it has to be changed later
                                                    temp2.update_load();
    
                                                }
                                            }
                                            // flag=0;
                                        }
                                        else
                                        {
                                            if(temp1.time==3)
                                            {
                                            System.out.println("Time slot is 3");
                                            if(i1==4)
                                            {
                                                if(temp2.times.get(i1)==null&&temp2.times.get(i1+1)==null&&temp2.times.get(i1+2)==null)
                                                {
                                                    flag=0;
                                                    temp2.set_time(courses.get(i),i1);
                                                    temp2.set_time(courses.get(i),i1+1); // it has to be changed later
                                                    temp2.set_time(courses.get(i),i1+2); // it has to be changed later
                                                    temp2.update_load();
    
    
                                                }
                                            }
                                            // flag=0;
    
                                            }
                                            else
                                            {
                                                System.out.println("Time slot is greater than 3");
                                                saturday.add(courses.get(i));
                                                flag=0;
                                            }
                                        }
                                    }
                                }
    
                                
                                   
                            }

                            if(flag==1)
                            {
                                System.out.println("Time slot is greater than 3");
                                saturday.add(courses.get(i));
                                flag=0;
                            }


                        }


                        

                    }

                }
            }

            for(int a=0;a<5;a++)

            {
                course_day.get(a).print();
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
        if (a.compareToIgnoreCase("NIL")==0){
            d_pref="";
        }
        else{
        d_pref=a;
        }

        if (b.compareToIgnoreCase("NIL")==0){
            t_pref="09001000";
        }
        else{
        t_pref = b;
        }
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
    public void set_time(myCourse a,int x){
        this.times.set(x,a);
    }
    public void update_load(){
        this.load+=1;
    }
    public void print(){
        // String jjj=name;

        System.out.println(this.name);
        for(int i=0;i<7;i++)
        {
            // System.out.println(i);
            if(this.times.get(i)!=null)
            {
                this.times.get(i).print();
                
            }
            // else{
            //     System.out.println();
            // }
        }
    }

};