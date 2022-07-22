
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Script;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

public class RhinoJavaScriptDemo {

    //http://www.tutorialsavvy.com/2012/12/rhino-javascript-library-for-java.html/
    public static void main(String[] args) {

        Context mozillaJsContext = Context.enter();
        Scriptable scope = mozillaJsContext.initStandardObjects();

        /*A Javascript JSON Object*/
        //String source = "{x = 2; y = 6; a :{x*y}}";
        int a = -1;
        System.out.println("int a (before update) = " + a);
        String source = "{x = 3; y = 5}";
        Script scriptjs = mozillaJsContext.compileString(source, "sandeepDemoScript", 1, null);
        /*Result is a Javascript Object*/
        Object jsObjectResult = scriptjs.exec(mozillaJsContext, scope);
        String result = Context.toString(jsObjectResult);
        System.out.println("After Evaluating JS Object value is: "+result);
        a = Integer.parseInt(result);
        System.out.println("int a (after update) = " + a);
    }

}
