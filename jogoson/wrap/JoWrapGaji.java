package jogoson.wrap;

public abstract class JoWrapGaji extends JoWrap {

    public abstract boolean isEmpty();

    @Override
    public JoWrap qetqet( String path ){
        return null;// TODO
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
