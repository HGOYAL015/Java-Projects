import static java.lang.Integer.MAX_VALUE;

import java.util.Scanner; // Import the Scanner class

class slots {
    int time;
    String d_pref, t_pref;
    slots(String a, String b, int n)
    {
        time=n;
        d_pref=a;
        t_pref=b;
    }

};

class myCourse {
    String coursename, tchr_name;
    int priority;
    int no_slots;
    slots A[] = new slots[no_slots];


     myCourse(String a,String b,String c,int n){
        
        coursename = a;
        tchr_name = b;
        
        if (c == "NIL")
            priority = MAX_VALUE;
        else
            priority = Integer.parseInt(c);
        no_slots=n;
        Scanner y = new Scanner(System.in);
        for (int i = 0; i < no_slots; i++) {
            
            n = y.nextInt();
            y.nextLine();   
            a= y.nextLine();
            b = y.nextLine();
            slots h=new slots(a,b,n);
            A[i]=h;
        }
        y.close();
       

    }


   
    public void print(){
        String info;
        info=String.format("%s %s %d %d", coursename,tchr_name,priority,no_slots);
        String j="";
        for(int i=0;i<no_slots;i++)
        {
            j=j+String.format("%d %s %s",A[i].time,A[i].d_pref,A[i].t_pref );
        }
        System.out.println(info+"\n");
        System.out.println(j+"\n");
    }

};

public class TimeTable {
    public static void main(String args[]) {
        Scanner x = new Scanner(System.in);

        int t = x.nextInt();
        for(var kk=0;kk<t;kk++){
            int no_course=x.nextInt();
            x.nextLine();
            myCourse courses[]=new myCourse[no_course];
            for(int i=0;i<no_course;i++)
            {
                String a=x.nextLine(),b=x.nextLine(),c=x.nextLine();
                int n=x.nextInt();
                x.nextLine();
                myCourse ss=new myCourse(a,b,c,n);
                courses[i]=ss;
            }
            for(var i=0;i<no_course;i++)
            {
               courses[i].print();
            }
            // t=t-1;
        }
        x.close();
    }
}