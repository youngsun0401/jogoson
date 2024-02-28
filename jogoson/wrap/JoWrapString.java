package jogoson.wrap;

public class JoWrapString extends JoWrapStr {
    private final String value;

    public JoWrapString( String value ){
        this.value = value;
    }

    @Override
    public Object getValue() {
        return value;
    }
}
