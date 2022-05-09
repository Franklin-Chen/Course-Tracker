package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents an instructor
public class Instructor implements Writable {

    private List<Integer> courseRatings;
    private String name;

    /*
     * EFFECTS:  constructs an instructor
     */
    public Instructor(String name) {
        this.name = name;
        courseRatings = new ArrayList<>();
    }

    // EFFECTS: returns instructorName
    public String getName() {
        return name;
    }

    // EFFECTS: returns courseRatings
    public List<Integer> getCourseRatings() {
        return courseRatings;
    }

    // EFFECTS: Finds the average course rating
    public double getAverageRating() {
        int rating = 0;
        int numCoursesTaught = 0;

        for (Integer i: courseRatings) {
            rating += i;
            numCoursesTaught += 1;
        }
        double averageRating = (double)rating / numCoursesTaught;

        if (numCoursesTaught == 0) {
            return 0;
        }
        return averageRating;
    }

    // REQUIRES: 1 <= i <= 10
    // MODIFIES: this
    // EFFECTS: adds a course rating
    public void addCourseRating(Integer i) {
        courseRatings.add(i);
    }

    // REQUIRES: 1 <= i <= 10
    // MODIFIES: this
    // EFFECTS: adds a course rating
    public void addCourseRatings(List<Integer> ratings) {
        courseRatings.addAll(ratings);
    }

    // MODIFIES: this
    // EFFECTS: removes a course rating
    public void removeCourseRating(int i) {
        int toRemove = -1;
        if (courseRatings.contains(i)) {
            for (int k = 0; k < courseRatings.size(); k++) {
                if (courseRatings.get(k) == i) {
                    toRemove = k;
                }
            }
        }
        if (toRemove >= 0) {
            courseRatings.remove(toRemove);
        }
    }


    //MODIFIES: this
    //EFFECTS: Puts the attributes to json
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("instructorName", name);
        json.put("courseRatings", courseRatings);
        return json;
    }
}

