package jogoson.wrap;

import java.lang.reflect.Constructor;
import java.util.Map;

import jogoson.Jogoson;

public class JoWrapMap extends JoWrapObj {
    private final Map<?, ?> value;

    public JoWrapMap( Map<?, ?> value ){
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
        return JoWrap.create( value.get(key) );
    }

    @Override
    public JoWrap get( int index ) {
        return JoWrap.create( value.get( String.valueOf(index) ) );
    }
}
