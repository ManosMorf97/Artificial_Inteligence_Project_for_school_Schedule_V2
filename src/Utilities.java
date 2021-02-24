import java.io.*;
import java.util.ArrayList;

public class Utilities {
    public  static Lesson passbyValue(Lesson lesson){
        if (lesson==null) return null;
        Lesson new_lesson=new Lesson();
        new_lesson.setCode(lesson.getCode());
        new_lesson.setNameCourse(lesson.getNameCourse());
        new_lesson.setClass(lesson.getClassABC());
        new_lesson.setAmmountofhours(lesson.getAmmount());
        new_lesson.setAmoh(lesson.getAmoh());
        new_lesson.setActive(lesson.Active());
        return new_lesson;
    }
    public static Teacher passbyValue(Teacher teacher){
        if (teacher==null) return null;
        Teacher new_teacher=new Teacher();
        new_teacher.setCode(teacher.getCode());
        new_teacher.setName(teacher.getName());
        new_teacher.setW(teacher.getW());
        for(int i=0; i<5; i++){
            new_teacher.setD(i,teacher.getD(i));
        }
        for(Lesson lesson: teacher.getCodesoflessons()){
            new_teacher.getCodesoflessons().add(Utilities.passbyValue(lesson));
        }
        return new_teacher;
    }
    public static State passbyValue(State state){
        if (state==null) return  null;
        ArrayList<Lesson> lessons=new ArrayList<>();
        ArrayList<Teacher> teachers=new ArrayList<>();
        for(Lesson lesson:state.getLessons())
            lessons.add(passbyValue(lesson));
        for(Teacher teacher:state.getTeachers())
            teachers.add(passbyValue(teacher));
        State new_state=new State(lessons,teachers);
        new_state.setX(state.getX());
        new_state.setY(state.getY());
        new_state.setZ(state.getZ());
        for(int i=0; i<5; i++){
            for(int j=0; j<7; j++){
                for(int k=0; k<9; k++){
                        new_state.setNameLesson(Utilities.passbyValue(state.getNameLesson(i,j,k)),i,j,k);
                        new_state.setNameTeacher(Utilities.passbyValue(state.getNameTeacher(i,j,k)),i,j,k);
                }
            }
        }
        return new_state;
    }
    public static StateNode getBestChild(int n,State state,int value,int depth){
        if(n==depth||state.isTerminal()){
            return new StateNode(state,value);
        }else{
            State states[]=new State[state.getLessons().size()];
            ArrayList<StateNode> SN=new ArrayList<>();
            int values[]=new int[states.length];
            int maxindex=0;
            for(int i=0; i<states.length; i++){
                states[i]=Utilities.passbyValue(state);
                boolean written=states[i].write(states[i].getLessons().get(i),false);
                values[i]=value;
                if(written) {
                    values[i] = value + states[i].getpriority();
                    SN.add(getBestChild(n + 1, states[i], values[i],depth));
                }
                if(!SN.isEmpty())
                    if(SN.get(SN.size()-1).getValue()>=SN.get(maxindex).getValue()) {
                        maxindex = SN.size()-1;
                    }
            }
            if(SN.isEmpty()) {
                state.write(null,true);
                return new StateNode(state,value);
            }
            return SN.get(maxindex);
        }
    }
    static void GO(String lessonstxt,String teacherstxt,String IQ){
        int max=0;//we will use it for evenly spread writing
        FileReader[]fr=new FileReader[2];
        BufferedReader[]br=new BufferedReader[2];
        ArrayList<Lesson> lessons=new ArrayList<Lesson>();
        ArrayList<Teacher> teachers=new ArrayList<Teacher>();
        int depth=Integer.parseInt(IQ);
        String[] arguments={lessonstxt,teacherstxt};
        try{
            for(int i=0; i<2; i++){
                fr[i]=new FileReader(arguments[i]);
                br[i]=new BufferedReader(fr[i]);
            }
            String line="";
            int i=0;
            int ll=0;
            while(line!=null){
                lessons.add(new Lesson());
                line=br[0].readLine();
                lessons.get(i).setCode(line.substring(line.indexOf(" ")+1));
                line=br[0].readLine();
                lessons.get(i).setNameCourse(line.substring(line.indexOf(" ")+1));
                line=br[0].readLine();
                lessons.get(i).setClass(line.substring(line.indexOf(" ")+1));
                line=br[0].readLine();
                lessons.get(i).setAmmountofhours(Integer.parseInt(line.substring(line.indexOf(" ")+1)));
                lessons.get(i).upL();
                line=br[0].readLine();
                ll=lessons.get(i).getNameCourse().length();
                if(max<ll) max=ll;//we want max length of lessons
                i++;
            }
            br[0].close();
            line="";
            i=0;
            String coursename;
            while(line!=null){
                teachers.add(new Teacher());
                line=br[1].readLine();
                teachers.get(i).setCode(line.substring(line.indexOf(" ")+1));
                line=br[1].readLine();
                teachers.get(i).setName(line.substring(line.indexOf(" ")+1));
                line=br[1].readLine();
                teachers.get(i).setMaxDay(Integer.parseInt(line.substring(line.indexOf(" ")+1)));
                line=br[1].readLine();
                teachers.get(i).setMaxWeek(Integer.parseInt(line.substring(line.indexOf(" ")+1)));
                line=br[1].readLine();
                String line2=line.substring(line.indexOf(" ")+1);
                while(line2!=null){
                    if(line2.indexOf(",")>=0)
                        coursename=line2.substring(0,line2.indexOf(","));
                    else
                        coursename=line2;
                    for(Lesson l:lessons){
                        if(l.getNameCourse().equals(coursename)){
                            teachers.get(i).addLesson(l);
                            break;
                        }
                    }
                    if(line2.indexOf(",")>=0)
                        line2=line2.substring(line2.indexOf(",")+1);
                    else
                        line2=null;
                }
                line=br[1].readLine();
                i++;
            }
            br[1].close();
        }catch(IOException e){
            System.out.println("Error during opening files");
        }
        State state=new State(lessons,teachers);
        while(!state.isTerminal()){
            state=Utilities.getBestChild(0,state,0,depth).getState();
        }
        Lesson [][][] program=state.Program();
        String day[]=new String[5];
        day[0]="Monday";
        day[1]="Tuesday";
        day[2]="Wednesday";
        day[3]="Thursday";
        day[4]="Firday";
        String sector[]=new String[9];
        sector[0]="A1";
        sector[1]="A2";
        sector[2]="A3";
        sector[3]="B1";
        sector[4]="B2";
        sector[5]="B3";
        sector[6]="C1";
        sector[7]="C2";
        sector[8]="C3";
        try{
            FileWriter fw=new FileWriter("schedule.txt");
            BufferedWriter bw=new BufferedWriter(fw);
            bw.write("Program of school\n");
            int yt;
            String wr;
            for(int x=0; x<5; x++){
                bw.write("\n"+day[x]+"\n");
                for(int y=0; y<7; y++){
                    yt=y+1;
                    bw.write(""+yt+".)");
                    for(int z=0; z<9; z++){
                        if(program[x][y][z]!=null&&program[x][y][z].getNameCourse()!=null){
                            bw.write(sector[z]+" "+program[x][y][z].getNameCourse());
                            wr=program[x][y][z].getNameCourse();
                        }
                        else {
                            bw.write(sector[z]+" "+"FREE TIME");
                            wr="FREE TIME";
                        }
                        for(int ui=0; ui<=(max-wr.length()); ui++)//evely spread writing
                            bw.write(" ");

                    }
                    bw.write("\n");
                }
            }
            bw.close();
            System.out.println("Done");
        }catch(IOException e){
            System.out.println("Error during writing in file");
        }
    }
}
