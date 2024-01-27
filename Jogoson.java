public class Jogoson {

    public Object parseValue( String s ){
        return parseValue(new StringToBeParsed(s));
    }

    public Object parseValue( StringToBeParsed s ){
        Object result;

        parseWhitespace(s);

        if( s.notDone() ){
            char c = s.check();
            switch( c ){
            case '"':
                result = parseString( s );
                break;
            default:
                throw new JsonParsingFailedException("no type of json value starts with '" + c + "'", s.i);
            }

            parseWhitespace(s);

            if( s.notDone() ){
                throw new JsonParsingFailedException("surplus characters after a valid json", s.i);
            }
 
        }else{
            result = null;
        }

        return result;
    }

    private String parseString( StringToBeParsed s ) {
        StringBuilder result = new StringBuilder();

        int state = 0;
        boolean more = true;
        while( more && s.notDone() ){
            char c = s.pop();
            switch( state ){
            case 0:// start
                if( c == '"' ){
                    state = 1;
                    break;
                }else{
                    throw new JsonParsingFailedException("a string value should starts with '\"'", s.i);
                }
            case 1:// in the string
                switch( c ){
                case '\\':
                    state = 2;
                    break;
                case '"':
                    state = -1;
                    break;
                // TODO control characters
                default:
                    result.append(c);
                }
                break;
            case 2:// escape
                switch( c ){
                case '\\':
                    result.append('\\');
                    state = 1;
                    break;
                case '"':
                    result.append('"');
                    state = 1;
                    break;
                // TODO others
                }
            case -1:// the string ends
                more = false;
                break;
            }
        }

        if( state != -1 ){
            throw new JsonParsingFailedException("the string does not ends with '\"'", s.i);
        }

        return result.toString();
    }

    public void parseWhitespace( StringToBeParsed s ){
        boolean more = true;
        while( more && s.notDone() ){
            char c = s.pop();
            switch( c ){
            case ' ':
            case '\t':
            case '\n':
            case '\r':
                break;
            default:
                s.back();
                more = false;
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

        public char check(){
            return cs[i];
        }

        public char pop(){
            return cs[i++];
        }

        public void back(){
            i -= 1;
        }
    }
}
