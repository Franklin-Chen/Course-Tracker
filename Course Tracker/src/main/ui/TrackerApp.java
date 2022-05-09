package ui;

import model.Course;
import model.Courses;
import model.Instructor;
import model.Instructors;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

// Course Tracker Application
public class TrackerApp {

    private Courses courses;
    private Instructors instructors;
    private Scanner input;
    private static final String JSON_STORE = "./data/StudyApp.json";
    private static final String JSON_STORE_INSTRUCTORS = "./data/Instructors.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JsonWriter jsonWriterInstructors;
    private JsonReader jsonReaderInstructors;

    // EFFECTS: runs the tracker application
    public TrackerApp() {
        runTeller();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runTeller() {
        boolean keepGoing = true;
        String command;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.nextLine();
            command = command.toLowerCase();

            if (command.equals("quit")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nSee You Later!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    @SuppressWarnings("methodlength")
    private void processCommand(String command) {
        switch (command) {
            case "a":
                addCourse();
                break;
            case "r":
                removeCourseAttributes();
                break;
            case "i":
                checkInstructorRating();
                break;
            case "c":
                checkCourseRating();
                break;
            case "v":
                viewCourses();
                break;
            case "s":
                viewInstructors();
                break;
            case "save":
                saveCourses();
                saveInstructors();
                break;
            case "load":
                loadCourses();
                loadInstructors();
            case "d":
                getAverage();
                break;
            default:
                System.out.println("Selection not valid...");
                break;
        }
    }


    // MODIFIES: this
    // EFFECTS: initializes assignments
    private void init() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriterInstructors = new JsonWriter(JSON_STORE_INSTRUCTORS);
        jsonReaderInstructors = new JsonReader(JSON_STORE_INSTRUCTORS);
        courses = new Courses();
        instructors = new Instructors();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta-> Add Course");
        System.out.println("\tr-> Remove Course");
        System.out.println("\ti-> Check Instructor Rating");
        System.out.println("\tc-> Check Course Rating");
        System.out.println("\td-> Check Course Average");
        System.out.println("\tv-> view Courses");
        System.out.println("\ts-> view Instructors");
        System.out.println("\tquit-> quit");
        System.out.println("\tsave -> save Courses");
        System.out.println("\tload -> loads Courses");
    }

    // MODIFIES: this
    // EFFECTS:  allows user to add a course and includes the rating of the course
    //           to the rating list of the course's instructor
    private void addCourse() {

        System.out.println("\nAdd the course name");
        String courseName = input.nextLine();
        System.out.println("\nAdd the course term");
        String term = input.nextLine();
        System.out.println("\nAdd the course rating");
        int courseRating = parseInt(input.nextLine());
        System.out.println("\nAdd the course rating");
        int gradeRecieved = parseInt(input.nextLine());
        System.out.println("\nAdd the Instructor teaching the course");
        String instructorName = input.nextLine();
        Instructor instructor = new Instructor(instructorName);
        boolean profExists = false;


        for (Instructor i : instructors.getInstructors()) {
            if (i.getName().equals(instructorName)) {
                instructor = i;
                profExists = true;
            }
        }

        if (!profExists) {
            instructors.addInstructor(instructor);
        }
        courses.addCourse(new Course(courseName, term, courseRating, gradeRecieved, instructor));

    }


    // EFFECTS: allows user to inputs which courses to remove, and calls remove course
    //          to remove the course with the selected attributes
    private void removeCourseAttributes() {
        System.out.println("\nChoose a course to remove");
        String courseName = input.nextLine();
        System.out.println("\nWhat term was it?");
        String term = input.nextLine();
        System.out.println("\nWhat was the Courses rating?");
        int rating = parseInt(input.nextLine());
        System.out.println("\nWho was the instructor for that course?");
        String instructorName = input.nextLine();

        int toRemove = -1;
        int instructorIndex = -1;
        Instructor ratingToRemove = new Instructor("");
        removeCourse(courseName, instructorName, term, rating, toRemove, instructorIndex, ratingToRemove);
    }

    // MODIFIES: this
    // EFFECTS: allows the user to remove a selected assignment and also removes
    //          the rating of the course from the rating list of the course's instructor
    private void removeCourse(String courseName, String instructorName, String term,
                              int rating, int toRemove, int instructorIndex, Instructor ratingToRemove) {

        for (int i = 0; i < courses.size(); i++) {
            if ((courses.get(i).getCourseName().equals(courseName))
                    && (courses.get(i).getCourseInstructor().getName().equals(instructorName))
                    && (courses.get(i).getCourseRating() == rating)
                    && (courses.get(i).getTerm().equals(term))) {
                toRemove = i;
                ratingToRemove = courses.get(i).getCourseInstructor();
            }
        }

        if (0 <= toRemove) {
            courses.removeCourse(toRemove);
            ratingToRemove.removeCourseRating(rating);

            for (int i = 0; i < instructors.size(); i++) {
                if (instructors.get(i).getCourseRatings().size() == 0) {
                    instructorIndex = i;
                }
            }
            if (0 <= instructorIndex) {
                instructors.removeInstructor(instructorIndex);
            }

        } else {
            System.out.println("Course Does not Exist");
        }

    }

    //EFFECTS: prints the average rating of the selected course
    public void checkCourseRating() {
        System.out.println("\nChoose a Course to Check");
        String toCheck = input.nextLine();
        int numCourses = 0;
        int totalRating = 0;
        List<Integer> coursesToCheck = new ArrayList<>();

        for (Course c : courses.getCourses()) {
            if (c.getCourseName().equals(toCheck)) {
                coursesToCheck.add(c.getCourseRating());
            }
        }
        for (Integer integer : coursesToCheck) {
            totalRating += integer;
            numCourses += 1;
        }

        if (numCourses == 0) {
            System.out.println("Invalid Course");
        }
        double averageRating = (double) totalRating / numCourses;
        System.out.println(averageRating);
    }

    private void getAverage() {
        System.out.println("\nChoose a Course to Check");
        String toCheck = input.nextLine();
        int numCourses = 0;
        int totalMarks = 0;
        List<Integer> coursesToCheck = new ArrayList<>();

        for (Course c : courses.getCourses()) {
            if (c.getCourseName().equals(toCheck)) {
                coursesToCheck.add(c.getGradeRecieved());
            }
        }
        for (Integer integer : coursesToCheck) {
            totalMarks += integer;
            numCourses += 1;
        }

        if (numCourses == 0) {
            System.out.println("Invalid Course");
        }
        double average = (double) totalMarks / numCourses;
        System.out.println(average);
    }

    //EFFECTS: prints the average rating of the selected instructor
    public void checkInstructorRating() {
        System.out.println("\nChoose an instructor to Check");
        String toCheck = input.nextLine();
        double rating = 0;

        for (Instructor i : instructors.getInstructors()) {
            if (i.getName().equals(toCheck)) {
                rating = i.getAverageRating();
            }
        }

        if (rating == 0) {
            System.out.println("instructor does not exist");
        }

        System.out.println(rating);
    }


    // MODIFIES: this
    // EFFECTS:  allows the user to view the attributes for all Courses
    private void viewCourses() {
        for (Course c : courses.getCourses()) {
            System.out.println("Course name: " + c.getCourseName());
            System.out.println("Course Rating: " + c.getCourseRating());
            System.out.println("Course grade: " + c.getGradeRecieved());
            System.out.println("Course Term: " + c.getTerm());
            System.out.println("Course Instructor: " + c.getCourseInstructor().getName());
        }

    }

    // MODIFIES: this
    // EFFECTS:  allows the user to view the attributes for all instructors
    private void viewInstructors() {
        for (Instructor i : instructors.getInstructors()) {
            System.out.println("Instructor name: " + i.getName());
            if (i.getCourseRatings().size() == 0) {
                System.out.println("Instructor does not teach any Courses");
            }
        }

    }

    // EFFECTS: saves the Courses to file
    public void saveCourses() {
        try {
            jsonWriter.open();
            jsonWriter.write(courses);
            jsonWriter.close();

            for (Course c: courses.getCourses()) {
                c.toJson();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads courses from file
    public void loadCourses() {
        try {
            courses = jsonReader.read();

            for (Course c: courses.getCourses()) {
                System.out.println("Loaded " + c.getCourseName() + " from " + JSON_STORE);
                System.out.println("Loaded " + c.getTerm() + " to " + JSON_STORE);
                System.out.println("Loaded " + c.getCourseRating() + " to " + JSON_STORE);
                System.out.println("Loaded " + c.getCourseInstructor().getName() + " to " + JSON_STORE);
            }
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: saves the Courses to file
    public void saveInstructors() {
        try {
            jsonWriterInstructors.open();
            jsonWriterInstructors.writeInstructor(instructors);
            jsonWriterInstructors.close();

            for (Instructor i: instructors.getInstructors()) {
                i.toJson();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_INSTRUCTORS);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads courses from file
    public void loadInstructors() {
        try {
            instructors = jsonReaderInstructors.readInstructors();

            for (Instructor i: instructors.getInstructors()) {
                System.out.println("Loaded " + i.getName() + " to " + JSON_STORE_INSTRUCTORS);
            }
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_INSTRUCTORS);
        }
    }


}

