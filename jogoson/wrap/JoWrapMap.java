package jogoson.wrap;

import java.util.Map;

public class JoWrapMap extends JoWrapObj {
    private final Map<?, ?> value;

    public JoWrapMap(  Map<?, ?> value ){
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
