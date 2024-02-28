package jogoson.wrap;

public class JoWrapFloat extends JoWrapNum {
    private final float value;

    public JoWrapFloat( float value ){
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
