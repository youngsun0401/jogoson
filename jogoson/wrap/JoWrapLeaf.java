package jogoson.wrap;

public abstract class JoWrapLeaf extends JoWrap {

    @Override
    public JoWrap get( String key ) {
        throw new JoWrapIllegalCallerException("cannot get from a leaf value");
    }

    @Override
    public JoWrap get( int index ) {
        throw new JoWrapIllegalCallerException("cannot get from a leaf value");
    }

    @Override
    public JoWrap qet( String key ){
        return new JoWrapNull();
    }

    @Override
    public JoWrap qet( int index ){
        return new JoWrapNull();
    }

    @Override
    public JoWrap qetqet( String path ) {
        return new JoWrapNull();
    }

    @Override
    public JoWrapObj asObj() {
        throw new JoWrapIllegalCallerException("this is not an object");
    }

    @Override
    public JoWrapArr asArr() {
        throw new JoWrapIllegalCallerException("this is not an array");
    }
}
