package com.mr00anderson.editor.jimgui;

import com.artemis.World;
import com.mr00anderson.editor.atremis.components.TestComponentComplex;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.cpp.DeallocatableObjectManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// This is the test drawable use to tes the class drawer. TODO remove
public class JImGuiEditorClazzDrawableTest implements JImGuiDrawable {

    /**
     * Simply a Logger Reference
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(JImGuiEditorClazzDrawableTest.class);

    public static final String WINDOW_TITLE = "Worlds View";
    public static final int WORLD_NAME_LENGTH = 255;


    /**
     * This is a setting that will persist when the world is saved
     */
    // Test serialization of components


    // When using reflection to setDataFromField or get component, its state needs to manage the
    // Native data type to in the same structure, the caller will need to purge the native and reflective wrapper

    JImGuiClazzDraw imGuiClazzDraw = new JImGuiClazzDraw();
    TestComponentComplex testComponentComplex = new TestComponentComplex();



    DeallocatableObjectManager deallocatableObjectManager = new DeallocatableObjectManager();



    // Need to create the world objects that may be needed for the UI - Clear color
    public JImGuiEditorClazzDrawableTest() {
//        deallocatableObjectManager.add(showMainMenuBar);

    }

    @Override
    public void draw(JImGui imGui, World world) {

        // TODO How to create widgets from basic building blocks and serialize them

        // Todo icon set


        // Main menu bars and pull the demo code over for the menu
        // Import the test demo as a help item in the debug menu




        // Temporary
        imGui.begin("Class Drawer");
        imGuiClazzDraw.drawSlowJavaClazz(imGui, 0, testComponentComplex);

        // Permanent

        // TODO Main Menu
        // TODO Debugger Window
        // TODO World Selector and Editor

    }

    @Override
    public void dispose() {
        deallocatableObjectManager.deallocateAll();
    }



}
