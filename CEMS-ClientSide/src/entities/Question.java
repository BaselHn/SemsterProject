package entities;

public class Question {

    private String ID;
    private String Question;
    private String Instruction;
    private String Answ_1;
    private String Answ_2;
    private String Answ_3;
    private String Answ_4;
    private String rightAnsw;
    private String  Teacher;
    
    
    public Question(String ID, 
    				String Question,  
    				String Instruction, 
    				String Answ_1, 
    				String Answ_2, 
    				String Answ_3, 
    				String Answ_4, 
    				String rightAnsw, 
    				String Teacher)
    {
    	this.ID          = ID; 
    	this.Question    = Question;
    	this.Instruction = Instruction;
    	this.Answ_1 	 = Answ_1;
    	this.Answ_2 	 = Answ_2;
    	this.Answ_3 	 = Answ_3;
    	this.Answ_4 	 = Answ_4;
    	this.rightAnsw   = rightAnsw;
    	this.Teacher     = Teacher;
    }
    
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getQuestion() {
		return Question;
	}
	public void setQuestion(String question) {
		Question = question;
	}
	public String getInstruction() {
		return Instruction;
	}
	public void setInstruction(String instruction) {
		Instruction = instruction;
	}
	public String getAnsw_1() {
		return Answ_1;
	}
	public void setAnsw_1(String answ_1) {
		Answ_1 = answ_1;
	}
	public String getAnsw_2() {
		return Answ_2;
	}
	public void setAnsw_2(String answ_2) {
		Answ_2 = answ_2;
	}
	public String getAnsw_3() {
		return Answ_3;
	}
	public void setAnsw_3(String answ_3) {
		Answ_3 = answ_3;
	}
	public String getAnsw_4() {
		return Answ_4;
	}
	public void setAnsw_4(String answ_4) {
		Answ_4 = answ_4;
	}
	public String getRightAnsw() {
		return rightAnsw;
	}
	public void setRightAnsw(String rightAnsw) {
		this.rightAnsw = rightAnsw;
	}

	public String getTeacher() {
		return Teacher;
	}

	public void setTeacher(String teacher) {
		Teacher = teacher;
	}
    
}
