package jogoson.wrap;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class JoWrap {

    static Map<Class<?>, Constructor<? extends JoWrap>> valueTypeToJowrapConstructor = new HashMap<>();

    static {
        registConstructor(Map.class, JoWrapMap.class);
        registConstructor(List.class, JoWrapList.class);
    }

    private static void registConstructor(Class<?> valueClass, Class<? extends JoWrap> JoWrapClass) {
        try {
            valueTypeToJowrapConstructor.put(valueClass, JoWrapClass.getDeclaredConstructor(valueClass));
        } catch (NoSuchMethodException | SecurityException e) {
            throw new RuntimeException("클래스 " + JoWrapClass.getSimpleName() + "에는 파라미터 " + valueClass.getSimpleName() + "를 받는 생성자가 없거나 접근이 안 됩니다.", e);// TODO
        }
    }

    public static JoWrap create( Object value ){
        if( value == null ){
            return JoWrapNull.instance;
        }

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
            return  JoWrapNull.instance;
        }
        return get( key );
    }

    public JoWrap qet( int index ){
        if( getValue() == null ){
            return JoWrapNull.instance;
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
