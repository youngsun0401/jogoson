package jogoson.wrap;

public class JoWrapChar extends JoWrapStr {
    private final char value;

    public JoWrapChar( char value ){
        this.value = value;
    }

    @Override
    public Object getValue() {
        return value;
    }
}
