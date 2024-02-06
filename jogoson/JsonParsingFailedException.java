package jogoson;
import jogoson.Jogoson.StringToBeParsed;

public class JsonParsingFailedException extends IllegalArgumentException {
    public JsonParsingFailedException( String msg, StringToBeParsed s, Throwable t ){
        super(
            ( s.i > s.cs.length )
                ? "failed to parse a json string at the end of string ... msg: " + msg
                : "failed to parse a json string at index " + --s.i + "... " + s + "  ... msg: " + msg,
            t);
    }
    public JsonParsingFailedException( String msg, StringToBeParsed s ){
        this(msg, s, null);
    }
    public JsonParsingFailedException( String msg ){
        super("failed to parse a json string at the end of string ... msg: " + msg);
    }
}
