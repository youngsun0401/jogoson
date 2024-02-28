package jogoson.wrap;

public abstract class JoWrapStr extends JoWrapLeaf {

    @Override
    public JoWrapNum asNum() {
        throw new JoWrapIllegalCallerException("this is not a number");
    }

    @Override
    public JoWrapStr asStr() {
        return this;
    }

    @Override
    public JoWrapBool asBool() {
        throw new JoWrapIllegalCallerException("this is not a boolean");
    }
}
