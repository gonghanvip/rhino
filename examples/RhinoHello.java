import org.mozilla.javascript.ContextFactory;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
//http://jar.fyicenter.com/3061_RhinoHello_java-Rhino_JavaScript_Hello_Example.html
public class RhinoHello {
    public static void main(String[] args) throws Exception {
        ContextFactory f = new ContextFactory();
        Context c = f.enterContext();
        Scriptable s = c.initStandardObjects();

        String js = "java.lang.System.out.println('Hello world!')";
        js = "java.lang.System.out.println('Hello world!')";

        c.evaluateString(s, js, null, 1, null);
    }
}