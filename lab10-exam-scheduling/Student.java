/**
 * student class to help adding data to graph
 */
public class Student {
    private String[] classes;
    private String name;

    /**
     * construct student with just name
     */
    public Student(String n) {
        name = n;
        classes = new String[4];
    }

    /**
     * construct student with:
     * @param n name
     * @param cs String array of class names
     */
    public Student(String n, String[] cs) {
        name = n;
        classes = cs;
    }

    /**
     * add a class to the student's classes
     * @param c class name
     * @param p index in String array
     */
    public void addClass(String c, int p) {
        classes[p] = c;
    }

    /**
     * @return the student's name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the array of Strings of class names
     */
    public String[] getClasses() {
        return classes;
    }

    /**
     * method to see what is inside the student
     */
    public String toString() {
        return name + " is taking: " + classes[0] + ", " + classes[1] + ", " + classes[2] + ", " + classes[3];
    }
}
