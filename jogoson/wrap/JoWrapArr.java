package jogoson.wrap;

public abstract class JoWrapArr extends JoWrapGaji {

    @Override
    public JoWrapObj asObj() {
        throw new JoWrapIllegalCallerException("this is not an object");
    }

    @Override
    public JoWrapArr asArr() {
        return this;
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
