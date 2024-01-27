public class Jogoson {

    public Object parseValue(String s){
        return parseValue(new StringToBeParsed(s));
    }

    public Object parseValue(StringToBeParsed s){
        parseWhitespace(s);
        // TODO value 알맹이
        parseWhitespace(s);

        return null;// TODO
    }

    public void parseWhitespace(StringToBeParsed s){
        // TODO
    }

    private class StringToBeParsed {
        int index = 0;// 이 위치를 처리할 차례
        char[] cs;

        public StringToBeParsed(String jsonString){
            cs = jsonString.toCharArray();
        }
    }
}
