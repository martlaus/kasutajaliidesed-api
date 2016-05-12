package kasutajaliidesedApi.rest.jackson.map;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;

public class LocalDateSerializer extends JsonSerializer<LocalDate> {

    private static DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T12:00:00.000Z'");
    private static DateTimeFormatter bcFormatter = DateTimeFormat.forPattern("yyyyyy-MM-dd'T12:00:00.000Z'");

    @Override
    public void serialize(LocalDate date, JsonGenerator gen, SerializerProvider arg2) throws IOException {

        if (date.getEra() == DateTimeConstants.BC) {
            gen.writeString(bcFormatter.print(date));
        } else {
            gen.writeString(formatter.print(date));
        }
    }
}
