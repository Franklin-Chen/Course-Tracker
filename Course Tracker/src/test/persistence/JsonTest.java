package persistence;

import model.Course;
import model.Instructor;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkCourse(String courseName, String term, int courseRating, Instructor instructor, Course course) {
        assertEquals(courseName, course.getCourseName());
        assertEquals(term, course.getTerm());
        assertEquals(courseRating, course.getCourseRating());
        assertEquals(instructor, course.getCourseInstructor());
    }

    protected void checkInstructor(String instructorName, Instructor instructor) {
        assertEquals(instructorName, instructor.getName());
    }
}
