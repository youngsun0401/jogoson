public class Jogoson {

    public Object parseValue( String s ){
        return parseValue(new StringToBeParsed(s));
    }

    public Object parseValue( StringToBeParsed s ){
        Object result;

        parseWhitespace(s);

        if( s.notDone() ){
            result = null;// TODO value 알맹이

            parseWhitespace(s);

            //// 남아있으면 에러
            if( s.notDone() ){
                throw new RuntimeException("surplus characters after a valid json");// TODO err
            }
 
        }else{
            result = null;
        }

        return result;
    }

    public void parseWhitespace( StringToBeParsed s ){
        boolean b = true;
        while( b && s.notDone() ){
            char c = s.pop();
            switch( c ){
            case ' ':
            case '\t':
            case '\n':
            case '\r':
                break;
            default:
                s.back();
                b = false;
            }
        }
    }

    private class StringToBeParsed {
        private int i = 0;// 이 위치를 처리할 차례
        private final char[] cs;

        public StringToBeParsed( String jsonString ){
            cs = jsonString.toCharArray();
        }

        public boolean notDone(){
            if( i < cs.length )
                return true;
            else
                return false;
        }

        public char pop(){
            return cs[i++];
        }

        public void back(){
            i -= 1;
        }
    }
}
