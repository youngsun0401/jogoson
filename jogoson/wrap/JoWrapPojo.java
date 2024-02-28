package jogoson.wrap;

public class JoWrapPojo extends JoWrapObj {
    private final Object value;

    public JoWrapPojo( Object value ){
        super();
        this.value = value;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public boolean isEmpty() {
        return false;// TODO
    }

    @Override
    public JoWrap get( String key ) {
        return null;// TODO
    }

    @Override
    public JoWrap get( int index ) {
        return null;// TODO
    }
}
