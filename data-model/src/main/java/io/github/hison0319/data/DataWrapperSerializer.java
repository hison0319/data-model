package io.github.hison0319.data;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

public class DataWrapperSerializer extends JsonSerializer<DataWrapper> {

    @Override
    public void serialize(DataWrapper value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        
        HashMap<String, Object> data = value.getDatas();

        Set<String> keys = data.keySet();
        for (String key : keys) {
            if (data.get(key) == null) {
                gen.writeNullField(key);
            } else if (data.get(key) instanceof String) {
                gen.writeStringField(key, (String)data.get(key));
            } else if (data.get(key) instanceof DataModelBase) {
                gen.writeFieldName(key);
                gen.writeTree(((DataModelBase)data.get(key)).getConvertJson());
            }
        }

        gen.writeEndObject();
    }
}
