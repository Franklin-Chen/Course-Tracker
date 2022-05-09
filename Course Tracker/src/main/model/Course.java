package model;

// Represents a Course with a course name, a term, a course Rating from 1 to 10, and an instructor


import org.json.JSONObject;
import persistence.Writable;

// Represents the Course
public class Course implements Writable {

    private String courseName;
    private String term;
    private int courseRating;
    private int gradeRecieved;
    private Instructor instructor;

    /*
     * REQUIRES: 1 <= courseRating <= 10 and courseName has a non-zero length
     * EFFECTS:  constructs a course with a courseName, a term, a courseRating, and an instructor
     *           as well as puts the courseRating into the list of ratings for the instructor
     */

    public Course(String courseName, String term, int courseRating, int gradeRecieved, Instructor instructor) {
        this.courseName = courseName;
        this.term = term;
        this.courseRating = courseRating;
        this.instructor = instructor;
        this.gradeRecieved = gradeRecieved;
        instructor.addCourseRating(this.courseRating);

    }

    //EFFECTS: returns term
    public String getTerm() {
        return term;
    }

    //EFFECTS: returns courseName
    public String getCourseName() {
        return courseName;
    }

    //EFFECTS: returns courseRating
    public int getCourseRating() {
        return courseRating;
    }

    public int getGradeRecieved() {
        return gradeRecieved;
    }

    //EFFECTS: returns instructor
    public Instructor getCourseInstructor() {
        return instructor;
    }


    //MODIFIES: this
    //EFFECTS: Puts the attributes to json
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("courseName", courseName);
        json.put("term", term);
        json.put("courseRating", courseRating);
        json.put("gradeRecieved", gradeRecieved);
        json.put("instructor", instructor.getName());
        //
        return json;
    }



}
