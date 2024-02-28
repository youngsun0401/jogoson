package jogoson.wrap;

public class JoWrapNull extends JoWrapLeaf {

    @Override
    public Object getValue() {
        return null;
    }

    @Override
    public boolean isNull(){
        return true;
    }

    @Override
    public JoWrapObj asObj() {
        throw new JoWrapIllegalCallerException("this is not an object");
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
