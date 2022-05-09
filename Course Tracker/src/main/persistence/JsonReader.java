package persistence;

import model.Course;
import model.Courses;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import model.Instructor;
import model.Instructors;
import org.json.*;

// Represents a reader that reads Courses from JSON data stored in file
// Code based on the data persistence sample
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads Courses from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Courses read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCourses(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses Courses from JSON object and returns it
    private Courses parseCourses(JSONObject jsonObject) {
        Courses c = new Courses();
        addCourses(c, jsonObject);
        return c;
    }

    // MODIFIES: courses
    // EFFECTS: parses Courses from JSON object and adds them to Courses
    private void addCourses(Courses courses, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("courses");
        for (Object json : jsonArray) {
            JSONObject nextCourse = (JSONObject) json;
            addCourse(courses, nextCourse);
        }
    }

    // MODIFIES: courses
    // EFFECTS: parses Course from JSON object and adds it to Courses
    private void addCourse(Courses courses, JSONObject jsonObject) {
        String courseName = jsonObject.getString("courseName");
        String term = jsonObject.getString("term");
        int courseRating = jsonObject.getInt("courseRating");
        int gradeRecieved = jsonObject.getInt("gradeRecieved");
        String name = jsonObject.getString("instructor");
        Instructor instructor = new Instructor(name);
        Course c = new Course(courseName, term, courseRating, gradeRecieved, instructor);
        courses.addCourse(c);
    }

    // EFFECTS: reads Instructors from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Instructors readInstructors() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseInstructors(jsonObject);
    }

    // EFFECTS: parses Instructors from JSON object and returns it
    private Instructors parseInstructors(JSONObject jsonObject) {
        Instructors instructors = new Instructors();
        addInstructors(instructors, jsonObject);
        return instructors;
    }

    // MODIFIES: instructors
    // EFFECTS: parses Instructors from JSON object and adds them to Courses
    private void addInstructors(Instructors instructors, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("instructors");
        for (Object json : jsonArray) {
            JSONObject nextCourse = (JSONObject) json;
            addInstructor(instructors, nextCourse);
        }
    }

    // MODIFIES: instructors
    // EFFECTS: parses Instructors from JSON object and adds it to Instructor
    private void addInstructor(Instructors instructors, JSONObject jsonObject) {
        String instructorName = jsonObject.getString("instructorName");
        Instructor instructor = new Instructor(instructorName);

        JSONArray array = jsonObject.getJSONArray("courseRatings");
        List<Object> javaArray = array.toList();
        ArrayList<Integer> courseRatings = new ArrayList<>(Arrays.asList(javaArray.toArray(new Integer[] {})));
        instructor.addCourseRatings(courseRatings);


        instructors.addInstructor(instructor);
    }





}



