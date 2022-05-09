package model;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InstructorTest {
    private Course a1;
    private Course a2;
    private Instructor testingInstructor;
    private ArrayList<Integer> bobCoursesTaught;


    @BeforeEach
    public void innit(){
        bobCoursesTaught = new ArrayList<>();
        bobCoursesTaught.add(4);
        testingInstructor = new Instructor("Bob");
        a1 = new Course("MATH 221", "2020", 4, testingInstructor);
        a2 = new Course("MATH 221", "2019", 5, testingInstructor);

    }

    @Test
    public void testGetName(){
        assertEquals("Bob", testingInstructor.getName());
    }

    @Test
    public void testGetPreviousRatings(){
        List<Integer> previousRatings = new ArrayList<>();
        previousRatings.add(4);

        previousRatings.add(5);

        assertEquals(previousRatings, testingInstructor.getCourseRatings());


    }

    @Test
    public void testGetAverageRatings(){
        assertEquals(4.5, testingInstructor.getAverageRating());
        testingInstructor.removeCourseRating(5);
        assertEquals(4, testingInstructor.getAverageRating());
        testingInstructor.removeCourseRating(4);
        assertEquals(0, testingInstructor.getAverageRating());
    }

    @Test
    public void testAddRating(){
        assertEquals(2, testingInstructor.getCourseRatings().size());
        testingInstructor.addCourseRating(5);
        assertEquals(3, testingInstructor.getCourseRatings().size());

    }

    @Test
    public void testRemoveRating(){
        assertEquals(2, testingInstructor.getCourseRatings().size());
        testingInstructor.removeCourseRating(5);
        assertEquals(1, testingInstructor.getCourseRatings().size());
        testingInstructor.removeCourseRating(999);
        assertEquals(1, testingInstructor.getCourseRatings().size());

    }

    @Test
    void testToJson() {

        JSONObject actual = new JSONObject();
        actual.put("instructorName", testingInstructor);
        actual.put("courseRatings", bobCoursesTaught);
        assertEquals(bobCoursesTaught.toString(), actual.get("courseRatings").toString());
        assertEquals(testingInstructor, actual.get("instructorName"));

    }
}
