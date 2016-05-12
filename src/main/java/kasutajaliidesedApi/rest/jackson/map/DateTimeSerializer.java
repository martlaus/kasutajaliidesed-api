package kasutajaliidesedApi.rest.jackson.map;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import kasutajaliidesedApi.utils.DateUtils;
import org.joda.time.DateTime;

import java.io.IOException;

public class DateTimeSerializer extends JsonSerializer<DateTime> {

    @Override
    public void serialize(DateTime date, JsonGenerator gen, SerializerProvider provider) throws IOException {

        gen.writeString(DateUtils.toJson(date));
    }
}
