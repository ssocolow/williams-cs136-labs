// courses are nodes, students taking two courses are edges
// each student has 6 edges associated with it (four choose 2)
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import structure5.*;
import java.util.Collection;
/**
 * class for functionality to schedule exams
 */
public class ExamScheduler {
    //make the graph to hold courses as verticies and a count on the edge, sharing same class
    private GraphList<String, Integer> graph = new GraphListUndirected<String, Integer>();
    //make a vector to hold students
    private Vector<Student> students = new Vector<>();

    private int numSlots = 0;
    private static int nUMRUNS = 10;

    /**
     * read in the input
     * make students
     * add students to exam sch
     */
    public static void main(String[] args) {
        //make the exam scheduler
        ExamScheduler[] manySchedules = new ExamScheduler[nUMRUNS];
        for (int i = 0; i < nUMRUNS; i++) {
            manySchedules[i] = new ExamScheduler();
        }
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String name = in.nextLine();
            String[] cs = new String[4];
            cs[0] = in.nextLine();
            cs[1] = in.nextLine();
            cs[2] = in.nextLine();
            cs[3] = in.nextLine();
            for (ExamScheduler es : manySchedules) {
                es.students.add(new Student(name, cs));
            }
        }
        //System.out.println(es.students);
        //add students to graph
        for (ExamScheduler es : manySchedules) {
                es.addStudentsToGraph();
        }
        //System.out.println(es.printGraph(es.graph));
        String[] examSched = new String[nUMRUNS];
        for (int i = 0; i < nUMRUNS; i++) {
                examSched[i] = manySchedules[i].getExamSchedule();
        }
        System.out.println("Currently running " + nUMRUNS + " trials");
        System.out.println("Best exam Schedule: \n" + examSched[getBestRun(manySchedules)]);
        System.out.println("Worst exam Schedule: \n" + examSched[getWorstRun(manySchedules)]);
    }

    /**
     * returns a best schedule
     */
    public static int getBestRun(ExamScheduler[] ms) {
        int lowest = 100000;
        for (int i = 0; i < nUMRUNS; i++) {
            if (ms[i].numSlots < lowest) {
                lowest = ms[i].numSlots;
            }
        }
        for (int i = 0; i < nUMRUNS; i++) {
            if (ms[i].numSlots == lowest) {
                return i;
            }
        }
        Assert.fail("Should've returned");
        return -1;

    }

    /**
     * returns a worst schedule
     */
    public static int getWorstRun(ExamScheduler[] ms) {
        int largest = 0;
        for (int i = 0; i < nUMRUNS; i++) {
            if (ms[i].numSlots > largest) {
                largest = ms[i].numSlots;
            }
        }
        for (int i = 0; i < nUMRUNS; i++) {
            if (ms[i].numSlots == largest) {
                return i;
            }
        }
        Assert.fail("Should have returned");
        return -1;
    }

    /**
     * shuffle student array before making the graph to allow for different solutions to be found
     * fisher yates shuffle algorithm
     */
    public void randomizeStudents(Vector<String> s) {
        for (int i = s.size() - 1; i > 0; i--) {
            int pos = (int) (Math.random() * (i + 1));
            String temp = s.get(pos);
            s.set(pos, s.get(i));
            s.set(i, temp);
        }
    }

    /**
     * returns an iterator to a shuffled students Vector
     */
    public Iterator<String> shuffleNodes(Iterator<String> it) {
        Vector<String> holder = new Vector<>();
        while (it.hasNext()) {
            holder.add(it.next());
        }
        randomizeStudents(holder);
        return holder.iterator();
    }

    /**
     * find the exam schedule
     * alter the graph
     * randomize
     */
    public String getExamSchedule() {
        String ret = "";
        int slot = 1;
        Iterator<String> it = shuffleNodes(graph.iterator());
        //loop through all the nodes
        while (it.hasNext()) {
            //this vector will hold all the classes in this exam block
            Vector<String> oneblock = new Vector<>();
            //first add the first node
            oneblock.add(it.next());

            //search the graph for nodes not connected
            Iterator<String> iter = shuffleNodes(graph.iterator());
            //loop through all nodes
            while (iter.hasNext()) {
                String csname = iter.next();
                //if the node isn't connected to any nodes in oneblock, add it to this exam time
                if (notConnectedTo(oneblock, csname)) {
                    oneblock.add(csname);
                }
            }
            
            ret += "Slot " + slot + ": ";
            //remove all the nodes in this exam block from the graph
            //and add them to ret
            for (String s : oneblock) {
                graph.remove(s);
                ret += s + " ";
            }
            ret += "\n";
            slot++;
            //reset the iterator to account for removed nodes
            it = graph.iterator();
        }
        numSlots = slot - 1;
        return ret;
    }

    /**
     * returns true if classname isn't connected to any nodes in the vector
     */
    public boolean notConnectedTo(Vector<String> vs, String classname) {
        for (String s : vs) {
            if (graph.getEdge(s, classname) != null || s.equals(classname)) {
                return false;
            }
        }
        return true;
    }
    /**
     * functionality to print a graph
     */
    public String printGraph(Graph<String, Integer> g) {
        Iterator<String> it = g.iterator();
        String ret = "";
        //iterate through all the nodes
        while (it.hasNext()) {
            String nodename = it.next();
            ret += "Node: " + nodename + " has edges with ";
            //get an iterator for the neighbors
            Iterator<String> iter = g.neighbors(nodename);

            while (iter.hasNext()) {
                String neighbor = iter.next();
                ret += neighbor + " (val: " + g.getEdge(nodename, neighbor).label() + ")" + ", ";
            }

            ret += "\n";
        }
        return ret;
    }

    /**
     * add a student to the graph
     * adds the class as a node if not already exists
     * then adds all the edges
     * @pre there are students
     */
    public void addStudentsToGraph() {
        Assert.pre(students.size() > 0, "no students");
        //loop through all the students
        for (Student s : students) {
            String[] classes = s.getClasses();
            //if the graph does not have the course, add it as a vertex
            for (int i = 0; i < 4; i++) {
                if (!graph.contains(classes[i])) {
                    graph.add(classes[i]);
                }
            }
            //add the edges
            for (int i = 0; i <= 3; i++) {
                for (int j = i + 1; j <= 3; j++) {
                    if (!graph.containsEdge(classes[i], classes[j])) {
                        graph.addEdge(classes[i], classes[j], 1);
                    } else if (!classes[i].equals(classes[j])) {
                        Integer c = graph.removeEdge(classes[i], classes[j]);
                        graph.addEdge(classes[i], classes[j], c + 1);
                    }
                }
            }
            Assert.post(graph.size() > 0, "graph is empty");
        }
    }
}