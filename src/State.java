

		import java.util.ArrayList;
public class State{
	private ArrayList<Lesson> lessons;
	private ArrayList<Teacher> teachers;
	private Lesson[][][] namelessons=new Lesson[5][7][9];//5 days 7 hours 9 classes
	private Teacher[][][] nameteachers=new Teacher[5][7][9];
	private int x=-1,y=0,z=0;
	public State(ArrayList<Lesson> lessonA,ArrayList<Teacher> teachersA){
		teachers=teachersA;
		lessons=lessonA;
	}
	public Lesson [][][] Program(){
		return namelessons;
	}
	public Teacher findTeacher(){
		ArrayList<Lesson>who;
		for(Teacher t:teachers){
			who=t.getCodesoflessons();
			for(Lesson cobralesson:who){
				if((cobralesson.getNameCourse()).equals(namelessons[x][y][z].getNameCourse())){
					return t;

				}
			}
		}
		return null;

	}

	public int getpriority(){
		int pr=0;
		if(!isTiredTeacher()) pr++;
		if(EvenlySpreadDays()) pr++;
		if(IsEvenlySpreadTeachers())pr++;
		if(namelessons[x][y][z].Active())pr++;
		return pr;
	}
	public ArrayList<Lesson> getLessons(){
		return lessons;
	}
	public ArrayList<Teacher> getTeachers(){
		return teachers;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getZ(){
		return z;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setZ(int z) {
		this.z = z;
	}
	public void setNameLesson(Lesson L,int i,int j,int k){
		namelessons[i][j][k]=L;
	}
	public void setNameTeacher(Teacher T,int i,int j,int k){
		nameteachers[i][j][k]=T;
	}
	public Lesson getNameLesson(int i,int j,int k){
		return namelessons[i][j][k];
	}
	public Teacher getNameTeacher(int i,int j,int k){
		return nameteachers[i][j][k];
	}
	public boolean IsAtTheSameTime(){
		if(nameteachers[x][y][z]==null) return false;
		for(int ip=0; ip<z; ip++){
			if(nameteachers[x][y][ip]==null) continue;
			if(nameteachers[x][y][z].getName().equals(nameteachers[x][y][ip].getName())) return true;
		}
		return false;
	}
	public boolean isTiredTeacher(){
		boolean b1=false,b2=false;
		if(y>1){
			for(int w1=0; w1<=z; w1++){
				if(nameteachers[x][y-1][w1]!=null)
					if(nameteachers[x][y][z].getName().equals(nameteachers[x][y-1][w1].getName()))b1=true;
				if(nameteachers[x][y-2][w1]!=null)
					if(nameteachers[x][y][z].getName().equals(nameteachers[x][y-2][w1].getName()))b2=true;
				if(b1&&b2)break;
			}
		}
		return (b1&&b2);
	}
	public boolean IsEvenlySpreadTeachers(){
		int m;
		for(int j=0; j<5; j++){
			for(int i=1; i<teachers.size(); i++){
				m=teachers.get(i).getD(j);
				if(m!=teachers.get(i-1).getD(j))
					return false;
			}
		}
		return true;
	}
	public boolean EvenlySpreadDays(){
		for(int z1=0; z1<z; z1++){
			int sum[]=new int[5];
			for(int x1=0; x1<x; x1++){
				sum[x1]=0;
				for(int y1=0; y1<y; y1++){
					if(namelessons[x1][y1][z1]!=null)
						sum[x1]++;
				}
				if(x1>=1)
					if(sum[x1]!=sum[x1-1]) return false;
			}
		}
		return true;
	}

	public boolean write(Lesson le,boolean nullptr ){
		GoNext();
		if(nullptr){
			this.namelessons[x][y][z]=null;
			this.nameteachers[x][y][z]=null;
			return true;
		}
		if(le==null||le.getNameCourse()==null)return false;
		this.namelessons[x][y][z]=le;
		this.nameteachers[x][y][z]=findTeacher();
		if(this.IsAtTheSameTime()||!this.namelessons[x][y][z].getClassABC().equals(n())||this.namelessons[x][y][z].getAmoh()==0||this.nameteachers[x][y][z].getD(x)==0||this.nameteachers[x][y][z].getW()==0) {
			return false;
		}
		this.namelessons[x][y][z].reduce();
		this.nameteachers[x][y][z].reduce(x);
		return true;
	}

	public void GoNext(){
		if(x==-1){
			x=0;
		}
		else if(x==4&&y==6&&z!=8){
			x=0;
			y=0;
			z++;
			for(Lesson LessonA:lessons)
				LessonA.upL();

		}
		else if(y==6){
			for(int i=0; i<7; i++)
				if(namelessons[x][i][z]!=null)
					namelessons[x][i][z].detonation();
			y=0;
			x++;
		}else
			y++;
	}


	public boolean isTerminal(){
		if(x!=4||y!=6||z!=8) return false;
		return true;
	}
	public String n(){
		if(z>=6) return "C";
		else if(z>=3) return "B";
		else return "A";
	}
}

