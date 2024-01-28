public class JsonParsingFailedException extends IllegalArgumentException {
    public JsonParsingFailedException( String msg, int index ){
        super(
            (index == -1)
            ? "failed to parse a json string at the end of string ... msg: "+ msg 
            : "failed to parse a json string at index " + index + " ... msg: "+ msg);
    }
}
