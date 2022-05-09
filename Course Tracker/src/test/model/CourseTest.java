package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {
    private Course a1;
    private Instructor Bob;
    private List<Integer> bobCoursesTaught;


    @BeforeEach

    public void innit(){
        bobCoursesTaught = new ArrayList<>();
        bobCoursesTaught.add(4);
        Bob = new Instructor("Bob");
        a1 = new Course("MATH 221", "2020", 4, Bob);

    }

    @Test
    public void testGetCourseName(){
        assertEquals("MATH 221", a1.getCourseName());
    }

    @Test
    public void testGetTerm(){
        assertEquals("2020", a1.getTerm());
    }

    @Test
    public void testGetCourseRating(){
        assertEquals(4, a1.getCourseRating());
    }

    @Test
    public void testGetInstructor(){
        assertEquals(Bob, a1.getCourseInstructor());
    }

    @Test
    void testToJson() {
        JSONObject actual = new JSONObject();
        actual.put("courseName", "CPSC 221");
        actual.put("term", "2020 W1");
        actual.put("courseRating", 5);
        actual.put("instructor", Bob);
        assertEquals("CPSC 221", actual.get("courseName"));
        assertEquals("2020 W1", actual.get("term"));
        assertEquals(5, actual.get("courseRating"));
        assertEquals(Bob, actual.get("instructor"));
    }
}