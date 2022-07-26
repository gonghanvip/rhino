import org.mozilla.javascript.Context;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

//https://stackoverflow.com/questions/1579777/rhino-javascript-how-to-convert-object-to-a-javascript-primitive/1579820#1579820
public class JavaToJS
{
    public static void main(String [] args) throws Exception {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName ("rhino");
        //Data data = new Data();
        Context.enter().getWrapFactory().setJavaPrimitiveWrap(false);
        try
        {
            //String clientCode = "return tf.get() + data";
            //TODO Find a way to attach library (npm, etc)
            String clientCode = "function my() {return 'me';}; function transform(tf, data) { var res = {}; res.x= my(); res.inp=data; return JSON.stringify(res);}";
            String ourCode = clientCode + "function run(tf, data) { return transform(tf, data); }";
            //System.out.println(ourCode);
            engine.eval(ourCode);
            System.out.println("Result:" + ((Invocable)engine).invokeFunction("run", new Data(), "input data"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
