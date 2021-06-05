package entities;

public class ExamResult {

	public String studentId;
	public String examId;
	public String score;
	
	public ExamResult(String sId, String exId, String scr)
	{
		studentId = sId;
		examId = exId;
		score = scr;
	}
	
	
	public String getStudentId()
	{
		return this.studentId;
	}
	
	public void setStudentId(String id)
	{
		this.studentId = id;
	}
	
	public String getExamId()
	{
		return this.examId;
	}
	
	public void setExamId(String id)
	{
		this.examId = id;
	}
	
	/*public String getCourse()
	{
		return this.curse;
	}
	
	public void setCourse(String c)
	{
		this.curse = c;
	}*/
	
	public String getScore()
	{
		return this.score;
	}
	
	public void setScore(String score)
	{
		this.score = score;
	}
	
}
