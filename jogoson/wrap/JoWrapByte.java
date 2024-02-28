package jogoson.wrap;

public class JoWrapByte extends JoWrapNum {
    private final byte value;

    public JoWrapByte( byte value ){
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
