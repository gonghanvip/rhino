import org.mozilla.javascript.*;
//import sun.org.mozilla.javascript.internal.*;
import java.io.IOException;

import java.util.*;


import static java.nio.file.Files.readAllBytes;

import static java.nio.file.Paths.get;


public class RhinoScripts {

    private static final SandboxClassShutter sandboxClassShutter = new SandboxClassShutter();

    private static ScriptableObject globalScope;

    public static void main(String[] args) throws IOException {

        RhinoScripts main = new RhinoScripts();

        main.execute();

    }


    public void execute() throws IOException {

        Context cx = Context.enter();

        cx.setOptimizationLevel(9);

        cx.setLanguageVersion(Context.VERSION_1_8);

        cx.setClassShutter((ClassShutter) sandboxClassShutter);

        try {

            Map result = new HashMap<>();

            Scriptable currentScope = getNewScope(cx);

            currentScope.put("result", currentScope, result);

            Script script = cx.compileString(new String(readAllBytes(get("fib_test.js"))), "my_script_id", 1, null);

            script.exec(cx, currentScope);

            System.out.println("Result: " + result.get("result"));

        } finally {

            Context.exit();

        }

    }



    private Scriptable getNewScope(Context cx) {

        //global scope lazy initialization

        if (globalScope == null) {

            globalScope = cx.initStandardObjects();

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a value for x: ");
            int x = scanner.nextInt();
            System.out.println("int x = " + x);
            globalScope.put("NUM_CONST", globalScope, x);

        }

        return cx.newObject(globalScope);

    }


    public static class SandboxClassShutter implements ClassShutter {

        public boolean visibleToScripts(String fullClassName) {

            return fullClassName.equals(HashMap.class.getName());

        }

    }

}