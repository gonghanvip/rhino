import org.mozilla.javascript.ContextFactory;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

public class UnitConversion {
    public static void main(String[] args) throws Exception {
        ContextFactory f = new ContextFactory();
        Context c = f.enterContext();
        Scriptable s = c.initStandardObjects();

        String js = "function p2k(p) {\n";
        js += "   return p*0.453592;\n";
        js += "}\n";
        js += "var k = p2k(p);";

        Double pound = Double.valueOf(args[0]);
        Object jsP = Context.javaToJS(pound, s);
        ScriptableObject.putProperty(s, "p", jsP);

        c.evaluateString(s, js, null, 1, null);
        Object jsK = ScriptableObject.getProperty(s, "k");
        Double kilo = (Double) Context.jsToJava(jsK, Double.class);

        System.out.println("Pound to Kilo conversion:");
        System.out.println("   Pound = "+pound);
        System.out.println("   Kilo = "+kilo);

        System.out.println("JavaScript used:");
        System.out.println(js);    }
}