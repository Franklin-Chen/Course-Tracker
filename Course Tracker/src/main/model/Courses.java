package model;

// Represents a List of Courses

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

public class Courses implements Writable {

    List<Course> courses;

    // Constructs the courses
    public Courses() {
        courses = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds c to courses
    public void addCourse(Course c) {
        courses.add(c);
    }

    // MODIFIES: this
    // EFFECTS: removes course at specified index
    public void removeCourse(int c) {
        courses.remove(c);
    }

    // EFFECTS: returns the size of the course
    public Integer size() {
        return courses.size();
    }

    // EFFECTS: returns courses
    public List<Course> getCourses() {
        return  courses;
    }

    // EFFECTS: returns the course at specified index
    public Course get(int i) {
        return courses.get(i);
    }

    //MODIFIES: this
    //EFFECTS: Put's courses to json
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("courses", coursesToJson());
        return json;
    }

    // MODIFIES: this
    // EFFECTS: returns course in courses as a JSON array
    private JSONArray coursesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Course c : courses) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }




}
