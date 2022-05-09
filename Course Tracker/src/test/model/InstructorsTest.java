package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InstructorsTest {


    private Courses testingCourses;
    private Course testingCourse;
    private Course testingCourseTwo;
    private Instructor testingInstructor;
    private Instructor testingInstructorTwo;
    private Instructors testingInstructors;


    @BeforeEach

    public void innit(){
        testingInstructors = new Instructors();
        testingCourses = new Courses();
        testingInstructor = new Instructor("Bob");
        testingInstructorTwo = new Instructor("Rob");
        testingCourse = new Course("MATH 221", "2020", 4, testingInstructor);
        testingCourseTwo = new Course("CPSC 210", "2020", 8, testingInstructorTwo);
    }

    @Test
    public void testAddInstructor(){
        assertEquals(0, testingInstructors.size());
        testingInstructors.addInstructor(testingInstructor);
        assertEquals(1, testingInstructors.size());
    }

    @Test
    public void testRemoveInstructor(){
        testingInstructors.addInstructor(testingInstructor);
        assertEquals(1, testingInstructors.size());
        testingInstructors.removeInstructor(0);
        assertEquals(0, testingInstructors.size());
    }

    @Test
    public void testGetInstructors(){
        List<Instructor> testing = new ArrayList<>();
        assertEquals(testing, testingInstructors.getInstructors());
        testingInstructors.addInstructor(testingInstructor);
        testing.add(testingInstructor);
        assertEquals(testing, testingInstructors.getInstructors());
    }

    @Test
    public void testGet(){
        testingInstructors.addInstructor(testingInstructor);
        testingInstructors.addInstructor(testingInstructorTwo);
        assertEquals(testingInstructor, testingInstructors.get(0));
        assertEquals(testingInstructorTwo, testingInstructors.get(1));
    }

    @Test
    void testToJson() {
        JSONObject actual = new JSONObject();
        actual.put("instructors", testingInstructors);
        assertEquals(testingInstructors, actual.get("instructors"));
    }

    @Test
    void coursesToJson() {
        JSONArray actual = new JSONArray();
        actual.put(testingInstructors.toJson());
        String expectedAnswer = "{\"instructors\":[]}";
        assertEquals(expectedAnswer, actual.get(0).toString());
    }
}
