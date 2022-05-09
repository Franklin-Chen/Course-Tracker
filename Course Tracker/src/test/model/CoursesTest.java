package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CoursesTest {

    private Courses testingCourses;
    private Course testingCourse;
    private Course testingCourseTwo;
    private Instructor testingInstructor;
    private Instructor testingInstructorTwo;


    @BeforeEach

    public void innit(){
        testingCourses = new Courses();
        testingInstructor = new Instructor("Bob");
        testingInstructorTwo = new Instructor("Rob");
        testingCourse = new Course("MATH 221", "2020", 4, testingInstructor);
        testingCourseTwo = new Course("CPSC 210", "2020", 8, testingInstructorTwo);
    }

    @Test
    public void testAddCourse(){
        assertEquals(0, testingCourses.size());
        testingCourses.addCourse(testingCourse);
        assertEquals(1, testingCourses.size());
    }

    @Test
    public void testRemoveCourse(){
        testingCourses.addCourse(testingCourse);
        assertEquals(1, testingCourses.size());
        testingCourses.removeCourse(0);
        assertEquals(0, testingCourses.size());
    }

    @Test
    public void testGetCourses(){
        List<Course> testing = new ArrayList<>();
        assertEquals(testing, testingCourses.getCourses());
        testingCourses.addCourse(testingCourse);
        testing.add(testingCourse);
        assertEquals(testing, testingCourses.getCourses());
    }

    @Test
    public void testGet(){
        testingCourses.addCourse(testingCourse);
        testingCourses.addCourse(testingCourseTwo);
        assertEquals(testingCourse, testingCourses.get(0));
        assertEquals(testingCourseTwo, testingCourses.get(1));
    }

    @Test
    void testToJson() {
        JSONObject actual = new JSONObject();
        actual.put("courses", testingCourse);
        assertEquals(testingCourse, actual.get("courses"));
    }

//indirect test through reader. test reader can
    @Test
    void coursesToJson() {
        JSONArray actual = new JSONArray();
        actual.put(testingCourse.toJson());
        String expectedOne = "{\"courseName\":\"MATH 221\","+
                "\"instructor\":\"Bob\",";
        String expectedTwo = "\"courseRating\":";
        int exptectedThree = 4;
        String exptectedFour = "," + "\"term\":\"2020\"}";

        String expectedAnswer = expectedOne + expectedTwo + exptectedThree + exptectedFour;
        assertEquals(expectedAnswer, actual.get(0).toString());
    }


}