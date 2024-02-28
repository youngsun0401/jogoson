package jogoson.wrap;

public class JoWrapLong extends JoWrapNum {
    private final long value;

    public JoWrapLong( long value ){
        this.value = value;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public JoWrapNum asNum() {
        return this;
    }
}
