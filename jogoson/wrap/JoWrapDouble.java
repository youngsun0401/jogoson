package jogoson.wrap;

public class JoWrapDouble extends JoWrapNum {
    private final double value;

    public JoWrapDouble( double value ){
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
