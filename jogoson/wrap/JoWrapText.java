package jogoson.wrap;

public class JoWrapText extends JoWrapStr {
    private final String value;

    public JoWrapText( String value ){
        this.value = value;
    }

    @Override
    public Object getValue() {
        return value;
    }
}
