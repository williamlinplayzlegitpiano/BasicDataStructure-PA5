import java.util.Objects;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Iterator;

public class Course {  
    
    HashSet<Student> enrolled;
    private final int capacity;
    private final String department;
    private final String number;
    private final String description;

    public Course (String department, String number, String description, int capacity) {

        if (department == null || number == null || description == null || capacity <= 0) {
            throw new IllegalArgumentException();
        }

        this.capacity = capacity;
        this.department = department;
        this.description = description;
        this.number = number;
        this.enrolled = new HashSet<>();
    }

    public String getDepartment() {
        return this.department;
    }

    public String getNumber() {
        return this.number;
    }

    public String getDescription() {
        return this.description;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public boolean enroll(Student student) {
        if (student == null) {
            throw new IllegalArgumentException();
        }

        if (this.enrolled.size() >= this.capacity) {
            return false;   
        }

        return this.enrolled.add(student);

    }

    public boolean drop(Student student) {
        if (student == null) {
            throw new IllegalArgumentException();
        }

        return this.enrolled.remove(student);

    }

    public void cancel() {
        this.enrolled.clear();
    }

    public boolean isFull() {
        return this.enrolled.size() >= this.capacity;
    }

    public int getEnrolledCount() {
        return this.enrolled.size();
    }

    public int getAvailableSeats() {
        return this.capacity - this.enrolled.size();
    }

    public HashSet<Student> getStudents() {
        return new HashSet<>(this.enrolled);
    }

    public ArrayList<Student> getRoster() {
        ArrayList<Student> roster = new ArrayList<>(this.enrolled);
        Collections.sort(roster);
        return roster;
    }

    @Override
    public String toString() {
        return String.format("%s %s [%d] %s", this.department, this.number, this.capacity, this.description);
    }

}
