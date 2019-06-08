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

    myCourse(String s) {

        String []  all=s.split(" ");
        coursename = all[0]+" "+all[1];
        tchr_name = all[2];
        String c=all[3];
        if (c.compareToIgnoreCase("NIL")==0){
            priority = MAX_VALUE;
            // System.out.println("sssss");
        }
        else{
            priority = Integer.parseInt(c);
        }
        no_slots = Integer.parseInt(all[4]);
       

    }
    public void addtimeslot(String s)
    {
        
        
        time_slot.add(new slots(s));
    }

    public void print() {
        String info;
        info = String.format("%s %s %d %d", coursename, tchr_name, priority, no_slots);
        String j = "";
        for(int i=0;i<no_slots;i++)
        {
            j+=String.format("%d %s %s", time_slot.get(i).time,time_slot.get(i).d_pref,time_slot.get(i).t_pref);
        }
        // System.out.println(info);
        // System.out.println(j );
    }
    public int get_slot(){
        return this.no_slots;
    }

};

public class TimeTable {
    public static void main(String args[]) {
        Scanner x = new Scanner(System.in);

        int t = x.nextInt();
        x.nextLine();

        for (var kk = 0; kk < t; kk++) {
            int no_course = x.nextInt();
            Vector<myCourse> courses=new Vector();
            int no_slo,max_slot=0;
            String sample;
            for (int i = 0; i < no_course; i++) {
                // System.out.println("Enter the Course,Instructor Name,Priority,No. of slots");

               courses.add(new myCourse(sample=x.nextLine()));
               String [] s=sample.split(" ");
               no_slo=Integer.parseInt(s[4]);
               if(max_slot<no_slo)
               max_slot=no_slo;
               for(int j=0;j<no_slo;j++){
                //    System.out.println("Enter the time of slot,Day preference of slot,Time Preference of Slot");
                   courses.get(i).addtimeslot(x.nextLine());
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


            // for (int i = 0; i < no_course; i++) {
            //     courses.get(i).print();
            // }
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
                        int time_start=0;

                        while(flag==1 && time_start<Time_pref.length())
                        {

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
                                        temp2.set_time(courses.get(i),time_no,s);
                                        temp2.update_load();

                                    }


                                }
                                else
                                {
                                    if(temp1.time==2)
                                    {
                                        
                                        // System.out.println("Time slot is 2");
                                        if(time_no==0||time_no==2||time_no==4||time_no==5)
                                        {
                                            if(temp2.times.get(time_no)==null&&temp2.times.get(time_no+1)==null)
                                            {
                                                flag=0;
                                                temp2.set_time(courses.get(i),time_no,s);
                                                temp2.set_time(courses.get(i),time_no+1,s); // it has to be changed later
                                                temp2.update_load();

                                            }
                                        }
                                        // flag=0;
                                    }
                                    else
                                    {
                                        if(temp1.time==3)
                                        {
                                        // System.out.println("Time slot is 3");
                                            if(time_no==4)
                                            {
                                                if(temp2.times.get(time_no)==null&&temp2.times.get(time_no+1)==null&&temp2.times.get(time_no+2)==null)
                                                {
                                                    flag=0;
                                                    temp2.set_time(courses.get(i),time_no,s);
                                                    temp2.set_time(courses.get(i),time_no+1,s); // it has to be changed later
                                                    temp2.set_time(courses.get(i),time_no+2,s); // it has to be changed later
                                                    temp2.update_load();


                                                }
                                            }                                     
                                        }
                                        else
                                        {
                                            // System.out.println("Time slot is greater than 3");
                                            saturday.add(courses.get(i));
                                            flag=0;
                                        }
                                    }
                                }                          
                            }



                            //other days
                            Vector<week> temporary=new Vector();
                            for(int j=0;j<5&&flag==1;j++)
                            {
                                String x5=Integer.toString(j+1);
                                if(Time_pref.indexOf(x5)==-1)
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
                                        temp2.set_time(courses.get(i),time_no,s);
                                        temp2.update_load();

                                    }


                                }
                                else
                                {
                                    if(temp1.time==2)
                                    {
                                        
                                        // System.out.println("Time slot is 2");
                                        if(time_no==0||time_no==2||time_no==4||time_no==5)
                                        {
                                            if(temp2.times.get(time_no)==null&&temp2.times.get(time_no+1)==null)
                                            {
                                                flag=0;
                                                temp2.set_time(courses.get(i),time_no,s);
                                                temp2.set_time(courses.get(i),time_no+1,s); // it has to be changed later
                                                temp2.update_load();

                                            }
                                        }
                                        // flag=0;
                                    }
                                    else
                                    {
                                        if(temp1.time==3)
                                        {
                                        // System.out.println("Time slot is 3");
                                            if(time_no==4)
                                            {
                                                if(temp2.times.get(time_no)==null&&temp2.times.get(time_no+1)==null&&temp2.times.get(time_no+2)==null)
                                                {
                                                    flag=0;
                                                    temp2.set_time(courses.get(i),time_no,s);
                                                    temp2.set_time(courses.get(i),time_no+1,s); // it has to be changed later
                                                    temp2.set_time(courses.get(i),time_no+2,s); // it has to be changed later
                                                    temp2.update_load();


                                                }
                                            }
                                        // flag=0;

                                        }
                                        else
                                        {
                                            // System.out.println("Time slot is greater than 3");
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
                                            temp2.set_time(courses.get(i),i1,s);
                                            temp2.update_load();
    
                                        }
    
    
                                    }
                                    else
                                    {
                                        if(temp1.time==2)
                                        {
                                            
                                            // System.out.println("Time slot is 2");
                                            if(i1==0||i1==2||i1==4||i1==5)
                                            {
                                                if(temp2.times.get(i1)==null&&temp2.times.get(i1+1)==null)
                                                {
                                                    flag=0;
                                                    temp2.set_time(courses.get(i),i1,s);
                                                    temp2.set_time(courses.get(i),i1+1,s); // it has to be changed later
                                                    temp2.update_load();
    
                                                }
                                            }
                                            // flag=0;
                                        }
                                        else
                                        {
                                            if(temp1.time==3)
                                            {
                                            // System.out.println("Time slot is 3");
                                                if(i1==4)
                                                {
                                                    if(temp2.times.get(i1)==null&&temp2.times.get(i1+1)==null&&temp2.times.get(i1+2)==null)
                                                    {
                                                        flag=0;
                                                        temp2.set_time(courses.get(i),i1,s);
                                                        temp2.set_time(courses.get(i),i1+1,s); // it has to be changed later
                                                        temp2.set_time(courses.get(i),i1+2,s); // it has to be changed later
                                                        temp2.update_load();
        
        
                                                    }
                                                }
                                            // flag=0;
    
                                            }
                                            else
                                            {
                                                // System.out.println("Time slot is greater than 3");
                                                saturday.add(courses.get(i));
                                                flag=0;
                                            }
                                        }
                                    }                          
                                }
    
    
                                
                                //other days
                                Vector<week> temporary1=new Vector();
                                for(int j1=0;j1<5&&flag==1;j1++)
                                {
                                    String x51=Integer.toString(j1+1);
                                    if(Time_pref.indexOf(x51)==-1)
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
                                            temp2.set_time(courses.get(i),i1,s);
                                            temp2.update_load();
    
                                        }
    
    
                                    }
                                    else
                                    {
                                        if(temp1.time==2)
                                        {
                                            
                                            // System.out.println("Time slot is 2");
                                            if(i1==0||i1==2||i1==4||i1==5)
                                            {
                                                if(temp2.times.get(i1)==null&&temp2.times.get(i1+1)==null)
                                                {
                                                    flag=0;
                                                    temp2.set_time(courses.get(i),i1,s);
                                                    temp2.set_time(courses.get(i),i1+1,s); // it has to be changed later
                                                    temp2.update_load();
    
                                                }
                                            }
                                            // flag=0;
                                        }
                                        else
                                        {
                                            if(temp1.time==3)
                                            {
                                            // System.out.println("Time slot is 3");
                                            if(i1==4)
                                            {
                                                if(temp2.times.get(i1)==null&&temp2.times.get(i1+1)==null&&temp2.times.get(i1+2)==null)
                                                {
                                                    flag=0;
                                                    temp2.set_time(courses.get(i),i1,s);
                                                    temp2.set_time(courses.get(i),i1+1,s); // it has to be changed later
                                                    temp2.set_time(courses.get(i),i1+2,s); // it has to be changed later
                                                    temp2.update_load();
    
    
                                                }
                                            }
                                            // flag=0;
    
                                            }
                                            else
                                            {
                                                // System.out.println("Time slot is greater than 3");
                                                saturday.add(courses.get(i));
                                                flag=0;
                                            }
                                        }
                                    }
                                }
    
                                
                                   
                            }

                            if(flag==1)
                            {
                                // System.out.println("Time slot is greater than 3");
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
            saturday.sort(new sortByCourse());
            System.out.println("Saturday");
            for(int a=0;a<saturday.size();a++)
            saturday.get(a).print();
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
};
class sortByLoad implements Comparator<week>
{
    //sort week in ascending order of load
    public int compare(week a,week b){
        return (a.load-b.load);
    }
};
class sortByCourse implements Comparator <myCourse>
{
    public int compare(myCourse a,myCourse b)
    {
        return a.coursename.compareTo(b.coursename);
    }

}




class slots {
    int time;
    String d_pref, t_pref;

    slots(String s) {
        String [] all=s.split(" ");
        time = Integer.parseInt(all[0]);
        if (all[1].compareToIgnoreCase("NIL")==0){
            d_pref="";
        }
        else{
        d_pref=all[1];
        }

        if (all[2].compareToIgnoreCase("NIL")==0){
            t_pref="";
        }
        else{
        t_pref = all[2];
        }
    }

};



//
// i am working on starting hours if starting hours are occupied or not
class week{
    String name;
    int day_no;
    Vector<myCourse> times=new Vector();
    Vector<Integer> which_slot=new Vector();
    int load;
    week(String week_name,int n){
        name=week_name;
        day_no=n;
       
        for(int i=0;i<7;i++)
        {
            times.addElement(null);
            which_slot.addElement(-1);
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
    public void set_time(myCourse a,int x,int a1){
        // System.out.println(this.name+" "+x+" "+a1);
        // a.print();
        this.times.set(x,a);
        this.which_slot.set(x,a1);
    }
    public void update_load(){
        this.load=this.load+1;
    }
    public void print(){
        // String jjj=name;

        System.out.println(this.name);
        for(int i=0;i<7;i++)
        {
            // System.out.println(i);
            if(this.times.get(i)!=null)
            {
                // this.times.get(i).print();
                
                int ha=this.which_slot.get(i);

                int ll=this.times.get(i).time_slot.get(ha).time;
                
                String NAME=this.times.get(i).coursename+" "+this.times.get(i).tchr_name;
                String ans;
                if(i==0)
                {
                    if(ll==1){
                        ans=String.format("9:00-10:00 %s",NAME);
                    }
                    else{
                        ans=String.format("9:00-11:00 %s",NAME);                       
                    }
                }
                else
                {
                    if(i==1)
                    {
                        ans=String.format("10:00-11:00 %s",NAME);                                               
                    }
                    else
                    {
                        if(i==2)
                        {
                            if(ll==1){
                                ans=String.format("11:15-12:15 %s",NAME);
                            }
                            else{
                                ans=String.format("11:15-1:15 %s",NAME);                       
                            }
                        }
                        else
                        {
                            if(i==3)
                            {
                                ans=String.format("12:15-1:15 %s",NAME);                                               
                            }
                            else
                            {
                                if(i==4)
                                {
                                    if(ll==1)
                                    {
                                        ans=String.format("3:00-4:00 %s",NAME);
                                    }
                                    else
                                    {
                                        if(ll==2)
                                        ans=String.format("3:00-5:00 %s",NAME);   
                                        else
                                        ans=String.format("3:00-6:00 %s",NAME);                    
                                    }
                                }
                                else
                                {
                                    if(i==5)
                                    {
                                        if(ll==1)
                                        {
                                            ans=String.format("4:00-5:00 %s",NAME);
                                        }
                                        else
                                        {
                                            
                                            ans=String.format("4:00-6:00 %s",NAME);   
                                            
                                        }                                       
                                    }
                                    else
                                    ans=String.format("5:00-6:00 %s",NAME);
                                   
                                }
                            }


                        }
                        
                    }
                }
                

                i+=(ll-1);
                
                System.out.println(ans);
                
            }
            // else{
            //     System.out.println();
            // }
        }
    }

};