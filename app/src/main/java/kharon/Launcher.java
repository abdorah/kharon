/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package kharon;

import kharon.core.Binder;
import kharon.core.Parser;
import kharon.core.Scanner;
import kharon.utilities.StringUtility;

/**
 * @author KOTBI Abderrahmane
 */
public class Launcher {

    private static void launch(String readPath, String writePath) {
        StringUtility.desactivateLogger();
        Scanner.scan(readPath).forEach(System.out::println);
        Parser.parse(Scanner.scan(readPath)).forEach(System.out::println);
        Binder.bind(Parser.parse(Scanner.scan(readPath)), writePath);
    }

    public static void main(String[] args) {
        String rootPath = ClassLoader.getSystemResource("test").getPath() + "/";
        String readPath = rootPath + "read.rexx";
        String writePath = rootPath + "write.rexx";
        launch(readPath, writePath);
    }
}
