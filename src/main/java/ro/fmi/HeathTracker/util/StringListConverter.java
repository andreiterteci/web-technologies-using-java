package ro.fmi.HeathTracker.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Converter
public class StringListConverter implements AttributeConverter<List<String>, String> {
    private static final String SPLIT_CHAR = ";";

    @Override
    public String convertToDatabaseColumn(List<String> stringList) {
        if (stringList == null) {
            return "";
        }
        return String.join(SPLIT_CHAR, stringList);
    }

    @Override
    public List<String> convertToEntityAttribute(String string) {
        if (string.equals("")) {
            return Collections.emptyList();
        }
        return Arrays.asList(string.split(SPLIT_CHAR));
    }
}
