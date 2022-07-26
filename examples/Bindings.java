import javax.script.SimpleBindings;

public class Bindings extends SimpleBindings
{
    @Override
    public Object put(String name, Object value)
    {
        Object result = super.put(name, value);
        return result;
    }

    @Override
    public Object get(Object key)
    {
        Object result = super.get(key);
        return result;
    }
}
