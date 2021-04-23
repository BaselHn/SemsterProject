package entity;

public class Exam {
	
	Integer ID;
	String subject;
	String course;
	Integer SolTimeMin;
	Integer MrkPerQues;
	
	
	public Exam (Integer ID, String subject, String course, Integer SolTimeMin, Integer MrkPerQues){
		
		this.ID = ID;
		this.subject = subject;
		this.course = course;
		this.SolTimeMin = SolTimeMin;
		this.MrkPerQues = MrkPerQues;
	}
	
	public int getExamID()
	{
		return ID;
	}
	
	public void setExamID(int ID)
	{
		this.ID  = ID;
	}
	
	public String getSubject()
	{
		return subject;
	}
	
	public void setSubject(String subject)
	{
		this.subject= subject;
	}
	
	public String getExamCourse()
	{
		return course;
	}
	
	public void setExamCourse(String course)
	{
		this.course = course;
	}
	
	
	public int getExamSolTime()
	{
		return SolTimeMin;
	}
	
	public void setExamSolTime(int SolTimeMin)
	{
		this.SolTimeMin = SolTimeMin;
	}
	
	
	public int getExamMrkQues()
	{
		return MrkPerQues;
	}
	
	public void setExamMrkQues(int MrkPerQues)
	{
		this.MrkPerQues = MrkPerQues;
	}

}
