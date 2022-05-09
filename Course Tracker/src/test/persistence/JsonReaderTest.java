package persistence;

import model.Course;
import model.Courses;
import model.Instructor;
import model.Instructors;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Courses courses = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }


    @Test
    void testReaderEmptyCourses() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyCourses.json");
        try {
            Courses c = reader.read();
            assertEquals(0, c.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralCourses() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralCourses.json");
        try {
            Courses courses = reader.read();
            List<Course> listCourse = courses.getCourses();
            assertEquals(1, courses.size());
            checkCourse("CPSC 210", "2020 W2", 8, listCourse.get(0).getCourseInstructor(), listCourse.get(0));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderEmptyInstructors() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyInstructors.json");
        try {
            Instructors instructors = reader.readInstructors();
            assertEquals(0, instructors.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralInstructors() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralInstructors.json");
        try {
            Instructors instructors = reader.readInstructors();
            List<Instructor> listInstructors = instructors.getInstructors();
            assertEquals(1, instructors.size());
            checkInstructor("ROB", listInstructors.get(0));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}