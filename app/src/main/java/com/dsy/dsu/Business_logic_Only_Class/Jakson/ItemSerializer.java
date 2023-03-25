package com.dsy.dsu.Business_logic_Only_Class.Jakson;


import android.database.Cursor;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class ItemSerializer extends StdSerializer<Cursor> {

    public ItemSerializer() {
        this(null);
    }

    public ItemSerializer(Class<Cursor> t) {
        super(t);
    }

    @Override
    public void serialize(
            Cursor value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        jgen.writeStartObject();
        jgen.writeNumberField("id", 1);
        jgen.writeStringField("itemName", "2");
        jgen.writeNumberField("owner", 3);
        jgen.writeEndObject();
    }
}
