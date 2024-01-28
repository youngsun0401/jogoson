import javax.management.RuntimeErrorException;

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
            case 'n':
                s.i++;
                if( s.notDone()
                 && s.pop() == 'u'
                 && s.notDone()
                 && s.pop() == 'l'
                 && s.notDone()
                && s.pop() == 'l'
                ){
                    result = null;
                }else{
                    throw new JsonParsingFailedException("invalid format (it seemed to be a 'null')", s.i);
                }
                break;
            case 't':
                s.i++;
                if( s.notDone()
                 && s.pop() == 'r'
                 && s.notDone()
                 && s.pop() == 'u'
                 && s.notDone()
                 && s.pop() == 'e'
                ){
                    result = true;
                }else{
                    throw new JsonParsingFailedException("invalid format (it seemed to be a 'true')", s.i);
                }
                break;
            case 'f':
                s.i++;
                if( s.notDone()
                 && s.pop() == 'a'
                 && s.notDone()
                 && s.pop() == 'l'
                 && s.notDone()
                 && s.pop() == 's'
                 && s.notDone()
                 && s.pop() == 'e'
                ){
                    result = false;
                }else{
                    throw new JsonParsingFailedException("invalid format (it seemed to be a 'false')", s.i);
                }
                break;
            case '-':
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                result = parseNumber( s );
                break;
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

    private Object parseNumber( StringToBeParsed s ) {// TODO 타입 옵션?
        StringBuilder result = new StringBuilder();
        
        int resultType = 1;// 1:정수 / 2:실수 / 3:실수(E어쩌구)
        char c;

        //// 부호
        c = s.check();
        if( c == '-' ){
            result.append('-');
            s.i++;
        }
        //// 정수 부분
        c = s.check();
        switch( c ){
        case '0':
            result.append('0');
            s.i++;
            break;
        case '1':
        case '2':
        case '3':
        case '4':
        case '5':
        case '6':
        case '7':
        case '8':
        case '9':
            boolean more = true;
            while( more && s.notDone() ){
                c = s.pop();
                switch( c ){
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    result.append(c);
                    break;
                default:
                    more = false;
                    break;
                }
            }
            break;
        default:
            throw new JsonParsingFailedException("invalid format (expected a number here)", s.i-1);
        }

        //// 소수점
        if( c == '.' ){
            resultType = 2;
            result.append('.');
            c = s.pop();
            switch( c ){
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                result.append(c);
                break;
            default:
                throw new JsonParsingFailedException("invalid format (expected a number after the decimal point)", s.i-1);
            }
            boolean more = true;
            while( more && s.notDone() ){
                c = s.pop();
                switch( c ){
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    result.append(c);
                    break;
                default:
                    more = false;
                    break;
                }
            }
        }

        //// 지수
        if( c == 'E' || c == 'e' ){
            resultType = 3;
            result.append('E');
            boolean more = true;
            //// 부호
            if( s.notDone() ){
                c = s.pop();
                if( c == '+' || c == '-' ){
                    result.append(c);
                }else{
                    s.back();
                }
                //// 숫자
                if( s.notDone() ){
                    c = s.pop();
                    switch( c ){
                    case '0':
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9':
                        result.append(c);
                        break;
                    default:
                        throw new JsonParsingFailedException("invalid format (expected a number here)", s.i-1);
                    }
                    while( more && s.notDone() ){
                        c = s.pop();
                        switch( c ){
                        case '0':
                        case '1':
                        case '2':
                        case '3':
                        case '4':
                        case '5':
                        case '6':
                        case '7':
                        case '8':
                        case '9':
                            result.append(c);
                            break;
                        default:
                            s.back();
                            more = false;
                            break;
                        }
                    }
                }else{
                    throw new JsonParsingFailedException("invalid format (expected a number here)", -1);
                }
            }else{
                throw new JsonParsingFailedException("invalid format (expected a number or '+' or '-' here)", -1);
            }

        }else{
            s.back();
        }

        switch( resultType ){
        case 1:
            return Integer.parseInt(result.toString());
        case 2:
        case 3:
            return Double.parseDouble(result.toString());
        default:
            throw new RuntimeException("expected never happen ... pnweuwpqioffkdjgsdfdybufgyugsf");
        }
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
                    throw new JsonParsingFailedException("a string value should start with '\"'", s.i-1);
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
            throw new JsonParsingFailedException("the string does not end with '\"'", s.i);
        }

        return result.toString();
    }

    private void parseWhitespace( StringToBeParsed s ){
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
