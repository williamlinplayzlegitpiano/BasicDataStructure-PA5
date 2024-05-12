import java.util.Objects;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Iterator;

public class Student implements Comparable<Student> {

    private final String firstName;
    private final String lastName;
    private final String PID;

    public Student (String firstName, String lastName, String PID) {
        if (firstName == null || lastName == null || PID == null) {
            throw new IllegalArgumentException();
        }

        this.firstName = firstName;
        this.lastName = lastName;
        this.PID = PID;

    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getPID() {
        return this.PID;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Student student = (Student) o;
        return Objects.equals(this.firstName, student.firstName) &&
        Objects.equals(this.lastName, student.lastName) &&
        Objects.equals(this.PID, student.PID);

    }

    @Override
    public int hashCode() {
        return Objects.hash(this.firstName, this.lastName, this.PID);
    }

    public int compareTo(Student o) {
        if (o == null) {
            throw new NullPointerException();
        }

        int lastNameCompare = this.lastName.compareTo(o.lastName);
        if (lastNameCompare != 0) {
            return lastNameCompare;
        }
        int firstNameCompare = this.firstName.compareTo(o.firstName);
        if (firstNameCompare != 0) {
           return firstNameCompare; 
        }
        return this.PID.compareTo(o.PID);

    }
    
}
