import java.util.Objects;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

public class CustomTester {
    @Test
    public void testStudentEquals() {
        Student student = new Student("William", "Lin", "A17503383");
        assertFalse(student.equals(null));
    }

    @Test
    public void testStudentCompareTo() {
        Student student1 = new Student("William", "Lin", "A17503383");
        Student student2 = new Student("William", "Lin", "B17503383");
        assertTrue(student1.compareTo(student2) < 0);
    }

    @Test
    public void testCourseDrop() {
        Course course = new Course("CSE", "12", "Data Structures", 3);
        Student student1 = new Student("William", "Lin", "A17503383");
        Student student2 = new Student("John", "Wick", "A12345678");
        course.enroll(student1);
        assertEquals(1, course.getEnrolledCount());
        assertFalse(course.drop(student2));
    }

    @Test
    public void testCourseEnroll() {
        Course course = new Course("CSE", "12", "Data Structures", 1);
        Student student1 = new Student("William", "Lin", "A17503383");
        Student student2 = new Student("John", "Wick", "A12345678");
        course.enroll(student1);
        assertEquals(1, course.getCapacity());
        assertEquals(1, course.getEnrolledCount());
        assertFalse(course.enroll(student2));
    }
    
    @Test
    public void testCourseGetRoster() {
        Course course = new Course("CSE", "12", "Data Structures", 100);
        ArrayList<Student> students = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Student student = new Student("Student", Integer.toString(i), Integer.toString(i));
            students.add(student);
            course.enroll(student);
        }

        Collections.sort(students);
        assertEquals(students, course.getRoster());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSanctConstructor() {
        
        Sanctuary sanctuary = new Sanctuary(-5, 5);
        
    }

    @Test
    public void testSanctRescuePartial() {
        Sanctuary sanctuary = new Sanctuary(5, 2);
        sanctuary.rescue("Dog", 3);
        assertEquals(3, sanctuary.getTotalAnimals());
        assertEquals(1, sanctuary.rescue("Dog", 3));
    }

    @Test 
    public void testSanctRescueMaxSpecies() {
        Sanctuary sanctuary = new Sanctuary(500, 1);
        sanctuary.rescue("Dog", 3);
        assertEquals(sanctuary.getTotalSpecies(), sanctuary.getMaxSpecies());
        assertEquals(300, sanctuary.rescue("Cat", 300));
    }

    @Test
    public void testSanctReleasePartial() {
        Sanctuary sanctuary = new Sanctuary(5, 1);
        sanctuary.rescue("Dog", 3);
        sanctuary.release("Dog", 1);
        assertEquals(2, sanctuary.countForSpecies("Dog"));
    }

    @Test
    public void testSanctReleaseTooMany() {
        Sanctuary sanctuary = new Sanctuary(5, 1);
        sanctuary.rescue("Dog", 3);
        assertThrows(IllegalArgumentException.class, 
		() -> sanctuary.release("Dog", 5));
    }

    @Test
    public void testSanctHelpClosingSanctuaryPartial() {
        Sanctuary sanctuary = new Sanctuary(10, 2);
        sanctuary.rescue("Dog", 3);
        sanctuary.rescue("Cat", 3);
        Sanctuary closingsanctuary = new Sanctuary(10, 2);
        closingsanctuary.rescue("Dog", 3);
        closingsanctuary.rescue("Cat", 3);
        assertEquals(4, sanctuary.helpClosingSanctuary(closingsanctuary));
        assertEquals(4,sanctuary.countForSpecies("Dog"));
        assertEquals(6, sanctuary.countForSpecies("Cat"));

    }

}
