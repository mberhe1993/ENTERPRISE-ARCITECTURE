package cs544.exercise17_2;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class StudentService {
	private StudentDAO studentdao;

	public StudentService() {
		studentdao = new StudentDAO();
	}

	@Transactional(propagation= Propagation.REQUIRES_NEW,readOnly = true)
	public Student getStudent(long studentid) {
		return studentdao.load(studentid);
	}

	public void setStudentDAO(StudentDAO studentDAO) {
		this.studentdao=studentDAO;
	}
}
