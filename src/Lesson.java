public class Lesson{
		private String code,nameCourse,class_s;
		private int ammountofhours;
		private int amoh;
		private boolean active;
		
		public int getAmoh(){
			return amoh;
		}
		public void upL(){
			amoh=ammountofhours;
			active=true;
		}
		public void setCode(String codee){
			code=codee;
		}
		public void setNameCourse(String nameCoursee){
			nameCourse=nameCoursee;
		}
		public void setClass(String classs){
			if(classs.equals("A")||classs.equals("B")||classs.equals("C"))
			class_s=classs;
		}
		public void setAmmountofhours(int amofhou){
			if(amofhou>=1&&amofhou<=7)
				ammountofhours=amofhou;
		}
		public void setAmoh(int amoh){
			this.amoh=amoh;
		}
		public void setActive(boolean active){
			this.active=active;
		}
		public String getCode(){
			return code;
		}
		public String getNameCourse(){
			return nameCourse;
		}
		public String getClassABC(){
			return class_s;
		}
		public int getAmmount(){
			return ammountofhours;
		}
		public void reduce(){//we will use it to put Lessons in program
			amoh--;
			active=false;
		}

	    public boolean Active(){
		return active;
	    }
	    public void detonation(){
	    	active=true;
	    }

	}