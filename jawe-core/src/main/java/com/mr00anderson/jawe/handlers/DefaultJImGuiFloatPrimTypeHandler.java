package com.mr00anderson.jawe.handlers;

import com.mr00anderson.jawe.wrappers.DataFieldMapper;
import com.mr00anderson.jawe.wrappers.NativeFloatDataFieldMapper;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.NativeFloat;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static com.mr00anderson.jawe.JaweJImGui.f;
import static com.mr00anderson.jawe.JaweJImGui.showHelpMarker;
import static org.ice1000.jimgui.JImGuiGen.nextColumn;

public class DefaultJImGuiFloatPrimTypeHandler implements JImGuiTypeHandler {

    // Cache
    protected transient Int2ObjectMap<Map<String, DataFieldMapper<NativeFloat>>> int2ObjectMap = new Int2ObjectOpenHashMap<>();

    @Override
    public void handle(JImGui imGui, int fieldCount, Field field, int instanceId, Object objectToDraw) {
        String fieldName = field.getName();
        Map<String, DataFieldMapper<NativeFloat>> objectMap = int2ObjectMap.computeIfAbsent(instanceId, value -> new HashMap<>(fieldCount, 1.0f));
        DataFieldMapper<NativeFloat> nativeFloatWrapperFloat = objectMap.computeIfAbsent(fieldName, (s) -> new NativeFloatDataFieldMapper(field, objectToDraw));
        // Set the native field from the app field, allowing the app to update the UI
        nativeFloatWrapperFloat.setFieldFromNative();


        if(imGui.inputFloat(field.getName(), nativeFloatWrapperFloat.getNativeData(), 1, 1, "%g")){
            // Was inputted, need to check TODO
            System.out.println("Text input received");
        }

        nativeFloatWrapperFloat.setNativeFromField();
        nextColumn();
        imGui.text("float");
        imGui.sameLine();
        showHelpMarker(imGui, f( "Signed range %,g to %,g", Float.MIN_VALUE, Float.MAX_VALUE));
        nextColumn();
        imGui.text("4");
        nextColumn();
    }

    @Override
    public void dispose() {
        int2ObjectMap.forEach((k, v) -> v.forEach((k2, v2) -> v2.dispose()));
        int2ObjectMap.clear();
    }

    @Override
    public void purge(int id) {
        Map<String, DataFieldMapper<NativeFloat>> remove = int2ObjectMap.remove(id);
        remove.forEach((k, v) -> v.dispose());
    }
}
