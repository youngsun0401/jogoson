public class JsonParsingFailedException extends IllegalArgumentException {
    public JsonParsingFailedException( String msg, int index ){
        super("failed to parse a json string at index " + index + " ... msg: "+ msg);
    }
}
