package jogoson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Jogoson {

    public Object parseValue( String ss ){
        StringToBeParsed s = new StringToBeParsed(ss);

        Object result = parseValue(s);

        if( s.notDone() ){
            throw new JsonParsingFailedException("surplus characters after a valid json");
        }

        return result;
    }

    private Object parseValue( StringToBeParsed s ){
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
                    throw new JsonParsingFailedException("invalid format (it seemed to be a 'null')", s);
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
                    throw new JsonParsingFailedException("invalid format (it seemed to be a 'true')", s);
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
                    throw new JsonParsingFailedException("invalid format (it seemed to be a 'false')", s);
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
            case '{':
                result = parseObject( s );
                break;
            case '[':
                result = parseArray( s );
                break;
            default:
                throw new JsonParsingFailedException("no type of json value starts with '" + c + "'", s);
            }

            parseWhitespace(s);
 
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
            s.i += 1;
            c = s.pop();
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
            throw new JsonParsingFailedException("invalid format (expected a number here)", s);
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
                throw new JsonParsingFailedException("invalid format (expected a number after the decimal point)", s);
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
                        throw new JsonParsingFailedException("invalid format (expected a number here)", s);
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
                    throw new JsonParsingFailedException("invalid format (expected a number here)", s);
                }
            }else{
                throw new JsonParsingFailedException("invalid format (expected a number or '+' or '-' here)", s);
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
                    throw new JsonParsingFailedException("a string value should start with '\"'", s);
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
                s.back();
                more = false;
                break;
            }
        }

        if( state != -1 ){
            throw new JsonParsingFailedException("the string does not end with '\"'", s);
        }

        return result.toString();
    }

    private Map<String, Object> parseObject( StringToBeParsed s ){
        char c;
        int state = 1;
        String key = null;
        Object value;
        Map<String, Object> result = new HashMap<>();
        c = s.pop();
        if( c == '{' ){
            while( s.notDone() ){
                switch( state ){
                case 1 :{
                    parseWhitespace(s);
                    c = s.pop();
                    if( c == '}' ){
                        return result;
                    }
                    s.back();
                    key = parseString(s);
                    state = 2;
                    break;
                }case 2 :{
                    parseWhitespace(s);
                    c = s.pop();
                    if( c == ':' ){
                        value = parseValue(s);
                        result.put(key, value);
                        c = s.pop();
                        switch( c ){
                        case ',':
                            state = 3;
                            break;
                        case '}':
                            return result;
                        default:
                            throw new JsonParsingFailedException("invalid oject format (expected ',' or '}' here)", s);
                        }
                    }else{
                        throw new JsonParsingFailedException("TODO", s);// TODO
                    }
                    break;
                }case 3 :{
                    parseWhitespace(s);
                    key = parseString(s);
                    state = 2;
                    break;
                }
                }
            }
            throw new JsonParsingFailedException("the object does not end", s);
        }else{
            throw new JsonParsingFailedException("the object does not start with '{'", s);
        }
    }

    private List<Object> parseArray( StringToBeParsed s ){
        char c;
        int state = 1;
        Object value;
        List<Object> result = new ArrayList<>();
        c = s.pop();
        if( c == '[' ){
            while( s.notDone() ){
                switch( state ){
                case 1 :{
                    parseWhitespace(s);
                    c = s.pop();
                    if( c == ']' ){
                        return result;
                    }
                    s.back();
                    state = 2;
                    break;
                }case 2 :{
                    value = parseValue(s);
                    result.add(value);
                    c = s.pop();
                    switch( c ){
                    case ',':
                        break;
                    case ']':
                        return result;
                    default:
                        throw new JsonParsingFailedException("invalid oject format (expected ',' or '}' here)", s);
                    }
                    break;
                }
                }
            }
            throw new JsonParsingFailedException("the object does not end", s);
        }else{
            throw new JsonParsingFailedException("the object does not start with '{'", s);
        }

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

    public class StringToBeParsed {
        int i = 0;// 이 위치를 처리할 차례
        final char[] cs;

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
            try{
                return cs[i++];
            }catch( ArrayIndexOutOfBoundsException e ){
                throw new JsonParsingFailedException("TODO", this);
            }
        }

        public void back(){
            i -= 1;
        }

        public String toString(){
            if( i >= cs.length ){
                return "END";
            }

            StringBuilder sb = new StringBuilder();
            int i1 = ( 0 < i-20 ) ? i-20 : 0;
            int i2 = ( cs.length > i+20 ) ? i+20 : cs.length;
            String s = new String(cs);
            s = s.replaceAll("\n", " ")
                 .replaceAll("\r", " ")
                 .replaceAll("\t", " ")
                 .replaceAll("\s", " ");
            sb.append(s.substring(i1, i));
            sb.append('{');
            sb.append('{');
            sb.append(' ');
            sb.append(s.substring(i, i+1));
            sb.append(' ');
            sb.append('}');
            sb.append('}');
            sb.append(s.substring(i+1, i2));
            return sb.toString();
        }
    }
}
