import com.sun.org.apache.xml.internal.utils.SystemIDResolver;
import org.mozilla.javascript.Context;
import java.io.FileNotFoundException;
import java.io.FileReader;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
//https://github.com/josdejong/mathjs/issues/108
public class MathJS {
    protected static String MATHJS_FILE = "math.js";
    protected ScriptEngine engine;

    public MathJS () throws FileNotFoundException, ScriptException {
        System.out.println("Entered Main");
        ScriptEngineManager manager = new ScriptEngineManager ();
        engine = manager.getEngineByName ("rhino");
        Context.enter().getWrapFactory().setJavaPrimitiveWrap(false);
        try {
            engine.eval(new FileReader(MATHJS_FILE));
            engine.eval("var math = global.mathjs();");
            engine.eval("var parser = Math.parser();");
            engine.eval("var precision = 14;");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String eval (String expr) throws ScriptException {
        String script = "math.format(parser.eval('" + expr + "'), precision);";
        return (String) engine.eval(script);
    }

    public static void main(String[] args) throws FileNotFoundException, ScriptException {
        MathJS math = new MathJS();
        System.out.println(math.eval("a = 4.5"));
        System.out.println(math.eval("1.2 * (2 + a)"));
        System.out.println(math.eval("5.08 cm in inch"));
        System.out.println(math.eval("sin(45 deg) ^ 2"));
        System.out.println(math.eval("9 / 3 + 2i") );
        System.out.println(math.eval("det([-1, 2; 3, 1])"));
    }
}