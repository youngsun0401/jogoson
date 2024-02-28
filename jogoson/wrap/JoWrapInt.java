package jogoson.wrap;

public class JoWrapInt extends JoWrapNum {
    private final int value;

    public JoWrapInt( int value ){
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
