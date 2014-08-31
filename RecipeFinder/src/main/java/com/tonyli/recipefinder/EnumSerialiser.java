package com.tonyli.recipefinder;

import java.lang.reflect.Type;

import org.apache.log4j.Logger;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.tonyli.recipefinder.dao.ds.Unit;

/**
 * convert json string and enum
 * @author Tony Li
 *
 */
public class EnumSerialiser implements JsonSerializer<Unit>, JsonDeserializer<Unit> {

	private static Logger logger = Logger.getLogger(EnumSerialiser.class);
	
	@Override
	public JsonElement serialize(Unit unit, Type arg1,
			JsonSerializationContext arg2) {
		return new JsonPrimitive(unit.toString());
	} 

	@Override
	public Unit deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		return Unit.valueOf(json.getAsString());
	}
}  