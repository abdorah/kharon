/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package kharon;

/**
 * @author KOTBI Abderrahmane
 */
public class Launcher {
    public String getGreeting() {
        return "Hello test!";
    }

    public static void main(String[] args) {
        System.out.println(new Launcher().getGreeting());
    }
}
