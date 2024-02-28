package jogoson.wrap;

import java.util.List;

public class JoWrapList extends JoWrapArr {
    private final List<?> value;

    public JoWrapList( List<?> value ){
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
