package jogoson.wrap;

public abstract class JoWrap {

    public static JoWrap create( Object value ){
        return null;// TODO
    }

    public abstract Object getValue();

    public boolean isNull(){
        if( getValue() == null )
            return true;
        return false;
    }

    public abstract JoWrap get( String key );

    public abstract JoWrap get( int index );

    public JoWrap qet( String key ){
        if( getValue() == null ){
            return new JoWrapNull();
        }
        return get( key );
    }

    public JoWrap qet( int index ){
        if( getValue() == null ){
            return new JoWrapNull();
        }
        return get( index );
    }

    public abstract JoWrap qetqet( String path );

    public abstract JoWrapObj asObj();

    public abstract JoWrapArr asArr();

    public abstract JoWrapNum asNum();

    public abstract JoWrapStr asStr();

    public abstract JoWrapBool asBool();
}
