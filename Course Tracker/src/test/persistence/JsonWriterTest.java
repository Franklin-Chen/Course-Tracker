package persistence;

import model.Course;
import model.Courses;
import model.Instructor;

import model.Instructors;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            Courses courses = new Courses();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyCourses() {
        try {
            Courses courses = new Courses();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyCourses.json");
            writer.open();
            writer.write(courses);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyCourses.json");
            courses = reader.read();

            assertEquals(0, courses.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterEmptyInstructors() {
        try {
            Instructors instructors = new Instructors();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyInstructors.json");
            writer.open();
            writer.writeInstructor(instructors);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyInstructors.json");
            instructors = reader.readInstructors();

            assertEquals(0, instructors.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


    @Test
    void testWriterGeneralCourses() {
        try {
            Courses courses = new Courses();
            courses.addCourse(new Course("CPSC 210", "2020 W2", 8, new Instructor("ROB")));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralCourses.json");
            writer.open();
            writer.write(courses);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralCourses.json");
            courses = reader.read();
            List<Course> listCourse = courses.getCourses();
            assertEquals(1, courses.size());
            checkCourse("CPSC 210", "2020 W2", 8, listCourse.get(0).getCourseInstructor(), listCourse.get(0));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralInstructors() {
        try {
            Instructors instructors = new Instructors();
            Instructor Bob = new Instructor("BOB");
            Bob.addCourseRating(10);
            Bob.addCourseRating(5);
            instructors.addInstructor(Bob);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralInstructors.json");
            writer.open();
            writer.writeInstructor(instructors);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralInstructors.json");
            instructors = reader.readInstructors();
            List<Instructor> listInstructors = instructors.getInstructors();
            assertEquals(1, instructors.size());
            checkInstructor("BOB", listInstructors.get(0));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}