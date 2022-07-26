import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Collectors;
//import java.io.File;


import static java.lang.System.load;


//base file: https://stackoverflow.com/questions/1579777/rhino-javascript-how-to-convert-object-to-a-javascript-primitive/1579820#1579820

/**
 * Using JS library within RHINO
 * In order to run a JS library within RHINO,
 * the library must be in UMD format (Universal Module Definition: https://github.com/umdjs/umd)
 * Then move the .js file in the source folder and you are good to go!
 */
public class JSLibrary
{
    private static String readFile(String s) {
        try {
            return Files.readAllLines(Paths.get(s)).stream().collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String [] args) throws Exception {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName ("rhino");
        Context.enter().getWrapFactory().setJavaPrimitiveWrap(false);
        Context context = Context.enter();
        Scriptable globalScope = context.initStandardObjects();

        //data data = new data();
        //https://mathjs.org/download.html
        // full bundle from unpkg: math.js (version 11.0.1, 191 kB, minified and gzipped)
        //math.js (version 11.0.1, 191 kB, minified and gzipped)
        engine.eval(readFile("math.js"));

        //https://vocajs.com/ umd builds for browser
        //voca.min.js minified production-ready, with source map
        engine.eval(readFile("voca.min.js"));

        try {
//            engine.eval("function test(data) {a = data.get(); return  a * a;};");
//            System.out.println("Return user input square by calling a java inner class \n"
//                    + "Result = " +  ((Invocable)engine).invokeFunction("test"));

            engine.eval("function test_A() {return math.derivative('x^2 + x', 'x').toString();}");
            System.out.println("Using math.js to evaulate the derivative of the following expression \n" +
            "x^2 + x \n" + "Result = " +  ((Invocable)engine).invokeFunction("test_A"));

            System.out.println();

            engine.eval("function test_B() {return v.countWords('There are seven words in this sentence');}");
            System.out.println("Using voca.min.js to find the # of words in the following sentence:\n" +
                    "There are seven words in this sentence \n" + "Result = " + ((Invocable)engine).invokeFunction("test_B"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static class data {
        Scanner scanner = new Scanner(System.in);
        public Number get() {
            System.out.println("Enter a number: ");
            return scanner.nextDouble();
        }
    }
}
