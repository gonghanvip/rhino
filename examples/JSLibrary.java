import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Scanner;


import static java.lang.System.load;

//Library Research
//1.
//https://stackoverflow.com/questions/56756477/how-to-run-es6-and-npm-deps-in-rhino

/*Library Research
1.
https://stackoverflow.com/questions/56756477/how-to-run-es6-and-npm-deps-in-rhino

2. https://github.com/killmag10/nodeschnaps
Seems promising, cannot install
 */


//base file: https://stackoverflow.com/questions/1579777/rhino-javascript-how-to-convert-object-to-a-javascript-primitive/1579820#1579820
public class JSLibrary
{
    public static void main(String [] args) throws Exception {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName ("rhino");
        data data = new data();
        Context.enter().getWrapFactory().setJavaPrimitiveWrap(false);
        //load("math.js");
        Context context = Context.enter();
        Scriptable globalScope = context.initStandardObjects();
        //Reader esprimaLibReader = new InputStreamReader(JSLibrary.class.getResourceAsStream("math.js"));
        //context.evaluateReader(globalScope, esprimaLibReader, "math.js", 1, null);
        try {
            engine.eval(" function test(data) {a = data.get(); return Math.ceil( Math.E * a);};");
            System.out.println("Result:" + ((Invocable)engine).invokeFunction("test", data));
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
