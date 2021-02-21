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
        State new_state=new State(lessons,teachers,state.getDepth());
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
}