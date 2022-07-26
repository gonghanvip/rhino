import com.sun.org.apache.xml.internal.utils.SystemIDResolver;
import org.mozilla.javascript.Context;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import javax.script.*;

//https://github.com/josdejong/mathjs/issues/108
public class MathJS {
    protected static String MATHJS_FILE = "math.js";
    protected ScriptEngine engine;

    // ...
    protected static String MATHJS_URL =
            "http://cdnjs.cloudflare.com/ajax/libs/mathjs/0.16.0/math.js";
// ...


    public MathJS () throws IOException, ScriptException {
        System.out.println("Entered Main");
        ScriptEngine engine = new ScriptEngineManager().getEngineByName ("JavaScript");
        //engine.getBindings(ScriptContext.ENGINE_SCOPE).put();
        Context.enter().getWrapFactory().setJavaPrimitiveWrap(false);
        Context.getCurrentContext().compileString(readFile("math.js"), "math.js", 0, null);
        try {
            engine.eval(new FileReader(MATHJS_FILE));
//            Bindings b = new Bindings().put("import math.js", );
//            engine.getContext().setBindings(b, ScriptContext.GLOBAL_SCOPE);
//            engine.eval("var math = global.mathjs();");
            engine.eval("var result = math.derivative('x^2 + x', 'x');");
            engine.eval("var parser = math.parser();");
            engine.eval("var precision = 14;");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String readFile(String s) throws IOException {
        return Files.readAllLines(Paths.get(s)).stream().collect(Collectors.joining(System.lineSeparator()));
    }
//
//    public String eval (String expr) throws ScriptException {
//        String script = "math.format(parser.eval('" + expr + "'), precision);";
//        return (String) engine.eval(script);
//    }

    public static void main(String[] args) throws FileNotFoundException, ScriptException, IOException {
        MathJS math = new MathJS();
//        System.out.println(math.eval("a = 4.5"));
//        System.out.println(math.eval("1.2 * (2 + a)"));
//        System.out.println(math.eval("5.08 cm in inch"));
//        System.out.println(math.eval("sin(45 deg) ^ 2"));
//        System.out.println(math.eval("9 / 3 + 2i") );
//        System.out.println(math.eval("det([-1, 2; 3, 1])"));
    }
}