import java.util.*;

public class QuestionBank {

    private static final List<Question> allQuestions = new ArrayList<>();

    static {

        // ================== JAVA BASICS (1–40) ==================
        allQuestions.add(new Question("Which keyword is used to inherit a class?",
                new String[]{"this", "super", "extends", "implements"}, 2));
        allQuestions.add(new Question("Default value of int variable?",
                new String[]{"0", "1", "null", "undefined"}, 0));
        allQuestions.add(new Question("Which method is entry point of Java program?",
                new String[]{"start()", "main()", "run()", "init()"}, 1));
        allQuestions.add(new Question("Which keyword is used to create object?",
                new String[]{"new", "create", "object", "class"}, 0));
        allQuestions.add(new Question("Size of int in Java?",
                new String[]{"2 bytes", "4 bytes", "8 bytes", "Depends"}, 1));
        allQuestions.add(new Question("Which is not a primitive type?",
                new String[]{"int", "float", "String", "char"}, 2));
        allQuestions.add(new Question("Which keyword prevents inheritance?",
                new String[]{"final", "static", "private", "protected"}, 0));
        allQuestions.add(new Question("Which keyword is used to define constant?",
                new String[]{"final", "static", "const", "constant"}, 0));
        allQuestions.add(new Question("Which operator compares object references?",
                new String[]{"==", "equals()", "!=", "compareTo()"}, 0));
        allQuestions.add(new Question("Which class is root of all classes?",
                new String[]{"Object", "System", "Class", "Base"}, 0));

        // 11–40
        for (int i = 11; i <= 40; i++) {
            allQuestions.add(new Question(
                    "Java basic question #" + i,
                    new String[]{"Option A", "Option B", "Option C", "Option D"},
                    i % 4
            ));
        }

        // ================== OOP (41–80) ==================
        allQuestions.add(new Question("Which supports multiple inheritance?",
                new String[]{"Class", "Interface", "Abstract class", "Object"}, 1));
        allQuestions.add(new Question("Which keyword refers to current object?",
                new String[]{"this", "super", "self", "object"}, 0));
        allQuestions.add(new Question("Method overloading is?",
                new String[]{"Compile time polymorphism", "Runtime polymorphism", "Inheritance", "Encapsulation"}, 0));
        allQuestions.add(new Question("Which keyword is used to call parent constructor?",
                new String[]{"this()", "parent()", "super()", "base()"}, 2));
        allQuestions.add(new Question("Which OOP principle hides data?",
                new String[]{"Inheritance", "Polymorphism", "Encapsulation", "Abstraction"}, 2));

        for (int i = 46; i <= 80; i++) {
            allQuestions.add(new Question(
                    "OOP question #" + i,
                    new String[]{"Encapsulation", "Inheritance", "Polymorphism", "Abstraction"},
                    i % 4
            ));
        }

        // ================== COLLECTIONS (81–120) ==================
        allQuestions.add(new Question("Which allows duplicate elements?",
                new String[]{"Set", "Map", "List", "Queue"}, 2));
        allQuestions.add(new Question("Which is synchronized?",
                new String[]{"ArrayList", "Vector", "HashMap", "HashSet"}, 1));
        allQuestions.add(new Question("Which maintains insertion order?",
                new String[]{"HashSet", "TreeSet", "LinkedHashSet", "Set"}, 2));
        allQuestions.add(new Question("Which collection stores key-value?",
                new String[]{"List", "Set", "Map", "Queue"}, 2));
        allQuestions.add(new Question("Which allows null key?",
                new String[]{"TreeMap", "Hashtable", "HashMap", "ConcurrentHashMap"}, 2));

        for (int i = 86; i <= 120; i++) {
            allQuestions.add(new Question(
                    "Collection question #" + i,
                    new String[]{"List", "Set", "Map", "Queue"},
                    i % 4
            ));
        }

        // ================== EXCEPTIONS & THREADS (121–160) ==================
        allQuestions.add(new Question("Which is checked exception?",
                new String[]{"IOException", "NullPointerException", "ArithmeticException", "ClassCastException"}, 0));
        allQuestions.add(new Question("Which keyword handles exception?",
                new String[]{"throw", "catch", "try", "throws"}, 2));
        allQuestions.add(new Question("Which method starts thread?",
                new String[]{"run()", "start()", "begin()", "execute()"}, 1));
        allQuestions.add(new Question("Which pauses thread execution?",
                new String[]{"sleep()", "wait()", "stop()", "yield()"}, 0));
        allQuestions.add(new Question("Which exception is unchecked?",
                new String[]{"IOException", "SQLException", "NullPointerException", "ClassNotFoundException"}, 2));

        for (int i = 126; i <= 160; i++) {
            allQuestions.add(new Question(
                    "Thread/Exception question #" + i,
                    new String[]{"Option A", "Option B", "Option C", "Option D"},
                    i % 4
            ));
        }

        // ================== SWING, JVM, IO (161–200) ==================
        allQuestions.add(new Question("Swing package is?",
                new String[]{"java.awt", "javax.swing", "java.util", "java.io"}, 1));
        allQuestions.add(new Question("Which component allows single selection?",
                new String[]{"JCheckBox", "JRadioButton", "JButton", "JLabel"}, 1));
        allQuestions.add(new Question("JVM stands for?",
                new String[]{"Java Virtual Machine", "Java Variable Method", "Joint Virtual Machine", "Java Verified Mode"}, 0));
        allQuestions.add(new Question("Which is used for file reading?",
                new String[]{"Scanner", "FileReader", "BufferedReader", "All of these"}, 3));
        allQuestions.add(new Question("Garbage collection frees?",
                new String[]{"Memory", "Threads", "Files", "CPU"}, 0));

        for (int i = 166; i <= 200; i++) {
            allQuestions.add(new Question(
                    "Advanced Java question #" + i,
                    new String[]{"Option A", "Option B", "Option C", "Option D"},
                    i % 4
            ));
        }
    }

    public static List<Question> getExamQuestions() {
        List<Question> copy = new ArrayList<>(allQuestions);
        Collections.shuffle(copy);
        return copy.subList(0, 50);
    }
}
