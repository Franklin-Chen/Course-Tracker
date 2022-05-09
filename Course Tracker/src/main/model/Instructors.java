package model;

// Represents a List of Instructors

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

public class Instructors implements Writable {

    List<Instructor> instructors;

    // Constructs the instructors
    public Instructors() {
        instructors = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds i to instructors
    public void addInstructor(Instructor i) {
        instructors.add(i);
    }

    // MODIFIES: this
    // EFFECTS: removes instructor at specified index
    public void removeInstructor(int c) {
        instructors.remove(c);
    }

    // EFFECTS: returns the size of the instructors
    public Integer size() {
        return instructors.size();
    }

    // EFFECTS: returns instructors
    public List<Instructor> getInstructors() {
        return  instructors;
    }

    // EFFECTS: returns the instructors at specified index
    public Instructor get(int i) {
        return instructors.get(i);
    }

    //MODIFIES: this
    //EFFECTS: Put's instructors to json
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("instructors", coursesToJson());
        return json;
    }

    // MODIFIES: this
    // EFFECTS: returns instructor in instructors as a JSON array
    private JSONArray coursesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Instructor i : instructors) {
            jsonArray.put(i.toJson());
        }

        return jsonArray;
    }






}
