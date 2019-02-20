package slide24testconstraints;

import java.util.ArrayList;
import java.util.List;

import slide24b.CourseSection;
import slide24b.Registration;
import slide24b.Student;

public class TestConstraint24b {

	public static void main(String[] args) {
		Student s1 = new Student();
		Student s2 = new Student();
		CourseSection c1 = new CourseSection();
		CourseSection c2 = new CourseSection();
		// the following four lines create four Registration instances
		Registration r1 = new Registration("A+", s1, c1);
		Registration r2 = new Registration("B", s2, c2);
		Registration r3 = new Registration("A", s1, c2);
		Registration r4 = new Registration("B+", s2, c1);
		// the next lines try to creates a second Registration instance for the s1/c1 pair
		// this is possible for a regular class and no exception is raised
		Registration r5 = null;
		try {
			r5 = new Registration("C", s1, c1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		List<Registration> rList = new ArrayList<Registration>();
		rList.add(r1);
		rList.add(r2);
		rList.add(r3);
		rList.add(r4);
		rList.add(r5);
		for (Registration r : rList) {
			if (r == null) {
				System.out.println("null");
			} else {
				System.out.println(r.getGrade() + "   " + r.getStudent().toString() + "   " + r.getCourseSection().toString());				
			}
		}

	}

}
