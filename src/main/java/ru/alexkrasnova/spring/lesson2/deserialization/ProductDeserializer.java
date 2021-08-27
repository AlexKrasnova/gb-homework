package ru.alexkrasnova.spring.lesson2.deserialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.DoubleNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.LongNode;
import ru.alexkrasnova.spring.lesson2.model.Product;

import java.io.IOException;
import java.math.BigDecimal;

public class ProductDeserializer extends StdDeserializer<Product> {

    public ProductDeserializer() {
        this(null);
    }

    public ProductDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Product deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {

        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        String[] strings = node.get("code").asText().split("-");

        StringBuilder name = new StringBuilder(strings[0]);
        name.setLength(name.length()-1);

        StringBuilder company = new StringBuilder(strings[1]);
        company.deleteCharAt(0);

        BigDecimal price = node.get("price").decimalValue();

        return new Product(name.toString(), company.toString(), price);
    }
}
