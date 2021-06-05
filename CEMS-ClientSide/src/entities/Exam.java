package entities;

public class Exam {

	private String ID;
	private String Questions;
	private String Time;
	private String IntrStd;
	private String IntrTch;
	private String ExecCode;
	
	
	public Exam(String ID,
				String Questions,
				String Time,
				String IntrStd,
				String IntrTch,
				String ExecCode)
	{
		this.ID = ID;
		this.Questions = Questions;
		this.Time = Time;
		this.IntrStd = IntrStd;
		this.IntrTch = IntrTch;
		this.ExecCode = ExecCode;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getQuestions() {
		return Questions;
	}

	public void setQuestions(String questions) {
		Questions = questions;
	}

	public String getTime() {
		return Time;
	}

	public void setTime(String time) {
		Time = time;
	}

	public String getIntrStd() {
		return IntrStd;
	}

	public void setIntrStd(String intrStd) {
		this.IntrStd = intrStd;
	}

	public String getIntrTch() {
		return IntrTch;
	}

	public void setIntrTch(String intrTch) {
		IntrTch = intrTch;
	}

	public String getExecCode() {
		return ExecCode;
	}

	public void setExecCode(String execCode) {
		this.ExecCode = execCode;
	}
	
}
