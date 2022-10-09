/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package kharon;

import kharon.core.Binder;
import kharon.core.Parser;
import kharon.core.Scanner;

/**
 * @author KOTBI Abderrahmane
 */
public class Launcher {

    private static void launch(String readPath, String writePath) {
        Binder.bind(Parser.parse(Scanner.scan(readPath)), writePath);
    }

    public static void main(String[] args) {
        String rootPath = "/home/kotbi/Documents/Projects/kharon/app/src/main/resources/test/";
        String readPath = rootPath + "read.rexx";
        String writePath = rootPath + "write.rexx";
        launch(readPath, writePath);
    }
}
