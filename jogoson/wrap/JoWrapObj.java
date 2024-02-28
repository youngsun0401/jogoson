package jogoson.wrap;

public abstract class JoWrapObj extends JoWrapGaji {

    @Override
    public JoWrapObj asObj() {
        return this;
    }

    @Override
    public JoWrapArr asArr() {
        throw new JoWrapIllegalCallerException("this is not an array");
    }

    @Override
    public JoWrapNum asNum() {
        throw new JoWrapIllegalCallerException("this is not a number");
    }

    @Override
    public JoWrapStr asStr() {
        throw new JoWrapIllegalCallerException("this is not a string");
    }

    @Override
    public JoWrapBool asBool() {
        throw new JoWrapIllegalCallerException("this is not a boolean");
    }
}
