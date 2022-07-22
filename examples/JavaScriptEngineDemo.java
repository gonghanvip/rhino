import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class JavaScriptEngineDemo {


    public static void main(String[] args) throws FileNotFoundException, ScriptException, NoSuchMethodException {

        File jsFile = new File("./demoJavaScriptFile.js");
        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine jsEngine = factory.getEngineByName("javascript");
        jsEngine.put("out", System.out);
        Reader reader = new FileReader(jsFile);
        jsEngine.eval(reader);
        Invocable invocableEngine = (Invocable) jsEngine;
        invocableEngine.invokeFunction("add",5,6);

    }

}
