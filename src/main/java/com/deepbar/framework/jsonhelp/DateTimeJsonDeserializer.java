package com.deepbar.framework.jsonhelp;

import com.deeporder.framework.util.DateUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.Date;

/**
 * Created by josh on 15/6/23.
 */
public class DateTimeJsonDeserializer extends JsonDeserializer<Date> {
    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return DateUtil.convertToDate(jsonParser.getText(), DateUtil.YYYY_MM_DD_HH_MM_SS);
    }
}
