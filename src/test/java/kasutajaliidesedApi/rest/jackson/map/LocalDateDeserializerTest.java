package kasutajaliidesedApi.rest.jackson.map;

import com.fasterxml.jackson.core.JsonParser;
import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;

@RunWith(EasyMockRunner.class)
public class LocalDateDeserializerTest {

    @TestSubject
    private LocalDateDeserializer deserializer = new LocalDateDeserializer();

    @Mock
    private JsonParser jp;

    /**
     * Tests when date is created at midnight in EEST time zone (GMT+0300)
     */
    @Test
    public void deserializeMidnigh() {
        deserialize("2015-03-27T21:00:00.000Z", new LocalDate(2015, 3, 27));
    }

    private void deserialize(String date, LocalDate expected) {
        try {
            expect(jp.getText()).andReturn(date);
        } catch (IOException e) {
        }

        replay(jp);

        LocalDate result = null;

        try {
            result = deserializer.deserialize(jp, null);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        verify(jp);

        assertEquals(expected, result);
    }
}
