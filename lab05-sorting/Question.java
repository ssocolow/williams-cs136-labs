//imports
import java.util.Scanner;
import structure5.Association;

public class Question {
    /**
     * Reads in the phonebook then
     * constructs vector of students then
     * calls functions that print out answer for all five questions
     */
    public static void main(String[] args) {
        //read input
        Scanner input = new Scanner(System.in);
        MyVector<Student> m = new MyVector<Student>();
        while (input.hasNextLine()) {
            String name = input.nextLine();
            String addresss = input.nextLine();


            long phoneNumber = input.nextLong();
            int suBox = input.nextInt();
            long homePhone = input.nextLong();
            input.nextLine();
            input.nextLine();

            //add a new student to the vector
            m.add(new Student(name, addresss, phoneNumber, suBox, homePhone));
        }
        //answer the questions
        questionOne(m);
        questionTwo(m);
        questionThree(m);
        questionFour(m);
        questionFive(m);
    }

    /**
     * Orders the list alphabetically
     * @post prints out the first name
     */
    public static void questionOne(MyVector<Student> list) {
        list.sort((Student s1, Student s2) -> {
            return s1.getName().compareTo(s2.getName());
        });
        System.out.println("Question 1: " + list.get(0).getName());
    }

    /**
     * Orders the list by ascending SU box numbers
     * @post prints out the name of the student with the smallest and largest SU box number
     */
    public static void questionTwo(MyVector<Student> list) {
        list.sort((Student s1, Student s2) -> {
            return s1.getSUBox() - s2.getSUBox();
        });
        System.out.println("Question 2: Smallest: " + list.get(0).getName() + " Largest: " + list.get(list.size() - 1).getName());
    }

    /**
     * Orders the lsit by the number of vowels ascending
     * @post prints out the name of the student with the most vowels
     */
    public static void questionThree(MyVector<Student> list) {
        list.sort((Student s1, Student s2) -> {
            return vowelCount(s1.getName()) - vowelCount(s2.getName());
        });
        System.out.println("Question 3: " + list.get(list.size() - 1).getName());
    }

    /**
     * helper method for determining how many vowels are in a string (excluding y)
     * @param String test
     * @return an integer number of vowels in the string
     */
    public static int vowelCount(String test) {
        test = test.toLowerCase();
        int count = 0;
        for (int i = 0; i < test.length(); i++) {
            if (test.charAt(i) == 'a' || test.charAt(i) == 'e' || test.charAt(i) == 'i' || test.charAt(i) == 'o' || test.charAt(i) == 'u') {
                count++;
            }
        }
        return count;
    }

    /**
     * order the vector of associations of people with the same address
     * @post prints out the address shared with the most students and their names
     */
    public static void questionFour(MyVector<Student> list) {
        //make a myvector of associations from addresses to number of inhabitants
        MyVector<Association<String, Integer>> as = new MyVector<>();

        //populate it by going through all students
        for (int i = 0; i < list.size(); i++) {
            //if the association vector already has that address
            if (as.contains(new Association<String, Integer>(list.get(i).getAddress(), 0))) {
                //get the index of the address in the vector
                int place = as.indexOf(new Association<String, Integer>(list.get(i).getAddress(), 0));
                //and then set that association's value to one plus its original value
                as.get(place).setValue(as.get(place).getValue() + 1);
            //otherwise add a new association to the vector with the address and 0 inhabitants
            } else {
                as.add(new Association<String, Integer>(list.get(i).getAddress(), 0));
            }
        }
        //sort the association vector
        as.sort((Association<String, Integer> s1, Association<String, Integer> s2) -> {
            return s1.getValue() - s2.getValue();
        });
        //the second to last element of the vector is what we want because the last element is UNKOWN
        System.out.println("Question 4: " + as.get(as.size() - 2).getKey() + " is the address shared by the most students");
        System.out.print("Their names are: ");
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getAddress().equals(as.get(as.size() - 2).getKey())) {
                System.out.print(list.get(i).getName() + ", ");
            }
        }
        System.out.println();
    }

    /**
     * order the vector of associations of area codes to how many people share them
     */
    public static void questionFive(MyVector<Student> list) {
        //make a myvector of associations from area codes to number of inhabitants
        MyVector<Association<Long, Integer>> as = new MyVector<>();

        //populate it by going through all students
        for (int i = 0; i < list.size(); i++) {
            //if the association vector already has that area code
            if (as.contains(new Association<Long, Integer>(list.get(i).getHomePhone() / 10000000, 0))) {
                //get the index of the area code in the vector
                int place = as.indexOf(new Association<Long, Integer>(list.get(i).getHomePhone() / 10000000, 0));
                //and then set that association's value to one plus its original value
                as.get(place).setValue(as.get(place).getValue() + 1);
            //otherwise add a new association to the vector with the area code and 0 inhabitants
            } else {
                as.add(new Association<Long, Integer>(list.get(i).getHomePhone() / 10000000, 0));
            }
        }
        //remove 0
        long t = 0;
        as.remove(new Association<Long, Integer>(t, 0));
        //sort the association vector
        as.sort((Association<Long, Integer> s1, Association<Long, Integer> s2) -> {
            return s1.getValue() - s2.getValue();
        });
        //the second to last element of the vector is what we want to start with because the last element is -1
        System.out.println("Question 5: Common area code -- How many students share that area code");
        //print the top ten area codes and how many students share it
        for (int i = 0; i < 10; i++) {
            System.out.print(as.get(as.size() - 1 - i).getKey());
            System.out.print(" -- ");
            int count = 0;
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).getHomePhone() / 10000000 == as.get(as.size() - 1 - i).getKey()) {
                    count++;
                }
            }
            System.out.println(count);
        }
    }
}