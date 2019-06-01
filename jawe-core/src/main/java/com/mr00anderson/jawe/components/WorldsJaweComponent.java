package com.mr00anderson.jawe.components;

import com.artemis.*;
import com.artemis.utils.ImmutableBag;
import com.artemis.utils.IntBag;
import com.mr00anderson.jawe.JaweDesktopEditor;
import com.mr00anderson.jawe.drawables.*;
import com.mr00anderson.jawe.handlers.ActivationHandler;
import com.mr00anderson.jawe.types.BasicApp;
import com.mr00anderson.jawe.types.WorldWrapper;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import org.ice1000.jimgui.flag.JImSelectableFlags;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

// TODO Further abstraction optional, can take a list of elements and rename this to JaweDrawableComponent
public class WorldsJaweComponent extends PooledComponent {

    public JaweDrawables worldsHeader;
    public JaweDrawables worldsData;

    // Cached windows for when worlds are unclicked and clicked
    public Map<String, JaweWindow> worlds = new TreeMap<>(String::compareTo);

    public WorldsJaweComponent(WorldWrapper worldWrapper) {

        // TODO ADD
        // TODO REMOVE
        // TODO CATEGORIES (All (just paths), Unloaded, Loaded, Simulating)
        //        jaweButton = new JaweButton();
//        jaweButton.label = "+";
//        jaweButton.onActivation = new ActivationHandler<JaweButton>() {
//            @Override
//            public void handle(JaweButton imGuiDrawable) {
//                // Draw temporary test input or file finder... TODO
//            }
//        };


        //        nameSelectable.draw(imGui);
//        JaweJImGui.NEXT_COLUMN.draw(imGui);
//        locationType.draw(imGui);
//        JaweJImGui.NEXT_COLUMN.draw(imGui);
//        locationPath.draw(imGui);
//        JaweJImGui.NEXT_COLUMN.draw(imGui);

//        // TODO Move this world into the main menu for editing
        worldsData = new JaweDrawables(
                new JaweSelectable(worldWrapper.name, JImSelectableFlags.SpanAllColumns, new ActivationHandler<JaweSelectable>() {
                    @Override
                    public void handle(JaweSelectable objectActivated) {
                        // Build entity and drop into world or remove it
                        if(objectActivated.selected){
                            BasicApp app = worldWrapper.renderingSystem.app;
                            if(app.getClass().isAssignableFrom(JaweDesktopEditor.class)){
                                // Check if exist
                                JaweDesktopEditor jaweDesktopEditor = (JaweDesktopEditor) app;
                                // Selectable reference

                                // SYSTEMS
                                ImmutableBag<BaseSystem> worldSystems = worldWrapper.world.getSystems();
                                LinkedList<Object> systemsDrawables = new LinkedList<>();

                                Collections.addAll(
                                        systemsDrawables,
                                        new JaweSeparator(),
                                        new JaweColumns("System - C", 3, true),
                                        new JaweText("Class Name"),
                                        new JaweNextColumn(),
                                        new JaweText(("Enabled")), // Todo Check Box, are you sure pop up
                                        new JaweNextColumn(),
                                        new JaweText("Fully Qualified Name"),
                                        new JaweNextColumn(),
                                        new JaweSeparator()
                                );

                                for (int i = 0; i < worldSystems.size(); i++) {
                                    BaseSystem baseSystem = worldSystems.get(i);
                                    Class<? extends BaseSystem> aClass = baseSystem.getClass();
                                    Collections.addAll(
                                            systemsDrawables,
                                            new JaweText(aClass.getSimpleName()),
                                            new JaweNextColumn(),
                                            new JaweText(String.valueOf(baseSystem.isEnabled())),// TODO Check box, ar you sure pop up
                                            new JaweNextColumn(),
                                            new JaweText(aClass.getCanonicalName()),
                                            new JaweNextColumn()
                                    );
                                }


                                // COMPONENT TYPES
                                ImmutableBag<ComponentType> componentTypes = worldWrapper.world.getComponentManager().getComponentTypes();
                                LinkedList<Object> componentTypeDrawables = new LinkedList<>();

                                Collections.addAll(
                                        componentTypeDrawables,
                                        new JaweSeparator(),
                                        new JaweColumns("Component - C", 4, true),
                                        new JaweText("Class Name"),
                                        new JaweNextColumn(),
                                        new JaweText(("Index")),
                                        new JaweNextColumn(),
                                        new JaweText(("Is Pooled")),
                                        new JaweNextColumn(),
                                        new JaweText("Fully Qualified Name"),
                                        new JaweNextColumn(),
                                        new JaweSeparator()
                                );

                                for (int i = 0; i < componentTypes.size(); i++) {
                                    ComponentType componentType = componentTypes.get(i);
                                    Class<? extends Component> aClass = componentType.getType();
                                    Collections.addAll(
                                            componentTypeDrawables,
                                            new JaweText(aClass.getSimpleName()),
                                            new JaweNextColumn(),
                                            new JaweText(String.valueOf(componentType.getIndex())),
                                            new JaweNextColumn(),
                                            new JaweText(String.valueOf(componentType.isPooled)),// TODO Check box, ar you sure pop up
                                            new JaweNextColumn(),
                                            new JaweText(aClass.getCanonicalName()),
                                            new JaweNextColumn()
                                    );
                                }


                                // TODO Insert Filtering option, or  wrap this in a filter, this way can edit groups or find entities of X composition
                                // Entities All
                                EntitySubscription entitySubscription = worldWrapper.world.getAspectSubscriptionManager().get(Aspect.all());
                                LinkedList<JImGuiDrawable> entityDrawables = new LinkedList<>();

//
//                        // TODO This is dynamic based on entity components
//                        Collections.addAll(
//                                entityDrawables,
//                                JaweJImGui.SEPARATOR,
//                                new JaweColumns("Entity - C", 4, true),
//                                new JaweText("Id"),
//                                JaweJImGui.NEXT_COLUMN,
//                                new JaweText(("")),
//                                JaweJImGui.NEXT_COLUMN,
//                                new JaweText(("Is Pooled")),
//                                JaweJImGui.NEXT_COLUMN,
//                                new JaweText("Fully Qualified Name"),
//                                JaweJImGui.NEXT_COLUMN,
//                                JaweJImGui.SEPARATOR
//                        );


                                IntBag entities = entitySubscription.getEntities();
                                int[] entityIds = entities.getData();

                                Int2ObjectMap<IntList> map = new Int2ObjectOpenHashMap<>();

                                // TODO Arch Type Mapper look up for friendly name mapping
                                for (int i = 0; i < entities.size(); i++) {
                                    int entityId = entityIds[i];
                                    int compositionIdentity = worldWrapper.world.getComponentManager().getIdentity(entityId);
                                    IntList integers = map.computeIfAbsent(compositionIdentity, IntArrayList::new);
                                    integers.add(entityId);
                                }

                                ReflectiveJaweClazzDraw jaweClazzDraw = new ReflectiveJaweClazzDraw();

                                map.forEach((archetypes, entityIdsV) -> {
//                                    JaweTreeNodeExNoPop archetypeTree = new JaweTreeNodeExNoPop(String.format("Archetype ID: %d", archetypes));
//                                    // Archtype Id Tree Pop
//                                    Collections.addAll(entityDrawables, archetypeTree, JaweJImGui.TREE_POP); // TODO Temp until String name for archetype
//
//                                    entityIdsV.forEach((IntConsumer) entityId -> {
//                                        JaweTreeNodeExNoPop entityTree = new JaweTreeNodeExNoPop(String.format("Entity ID: %d", entityId));
//
//                                        Collections.addAll(archetypeTree.worldsHeader, entityTree);
//
//                                        Bag<Component> components = new Bag<>();
//                                        worldWrapper.world.getComponentManager().getComponentsFor(entityId, components);
//
//                                        for (int i = 0; i < components.size(); i++) {
//                                            Component component = components.get(i);
//                                            JaweTreeNodeExNoPop componentsTree = new JaweTreeNodeExNoPop(
//                                                    String.format("Component %s", component),
//                                                    // UPDATE CLAZZ DRAW AND ALSO SO
////                                                imGui -> jaweClazzDraw.drawSlowJavaClazz(imGui, entityId, component),
//                                                    JaweJImGui.TREE_POP
//                                            );
//                                            // Entity Id Tree Pop
//                                            Collections.addAll(entityTree.worldsHeader, componentsTree, JaweJImGui.TREE_POP);
//                                        }
//                                    });
                                });


                                JaweWindow jaweWindow = new JaweWindow(
                                        worldWrapper.name,
                                        new JaweText("TODO - World Meta Data"),
                                        // TODO TABS
                                        new JaweCollapsingHeader(
                                                "Systems",
                                                systemsDrawables
                                        ),
                                        new JaweColumns("", 1, false),
                                        new JaweCollapsingHeader(
                                                "Component Types",
                                                componentTypeDrawables
                                        ),
                                        new JaweColumns("", 1, false),
                                        new JaweCollapsingHeader(
                                                "Entities",
                                                entityDrawables
                                        ),
                                        new JaweColumns("", 1, false)
                                );
                                worlds.putIfAbsent(worldWrapper.name, jaweWindow);
                                jaweDesktopEditor.getEditorWorldBuilder().addToWorld(jaweWindow);
                            } else {
                                // TODO ERROR should never happen as a worlds rendering system app should be a
                                // jawe editor here and nothing else
                                System.err.println("TODO ERROR JAWE SELECTABLE COULD NOT BE DRAWN");
                            }
                        } else {
                            BasicApp app = worldWrapper.renderingSystem.app;
                            if(app.getClass().isAssignableFrom(JaweDesktopEditor.class)){
                                JaweDesktopEditor jaweDesktopEditor = (JaweDesktopEditor) app;
                                jaweDesktopEditor.getEditorWorldBuilder().removeFromWorld(worldWrapper.name);
                                worlds.remove(worldWrapper.name);
                            } else {
                                // TODO ERROR should never happen as a worlds rendering system app should be a
                                // jawe editor here and nothing else
                                System.err.println("TODO ERROR JAWE SELECTABLE COULD NOT BE DRAWN");
                            }
                        }
                    }
                }),
                new JaweNextColumn(),
                new JaweText(worldWrapper.someLocation.type.toString()),
                new JaweNextColumn(),
                new JaweText(worldWrapper.someLocation.path),
                new JaweNextColumn()
        );














        worldsHeader = new JaweDrawables(
                // ADD
//                new JaweButton(" + ", new ActivationHandler<JaweButton>() {
//                    @Override
//                    public void handle(JaweButton imGuiDrawable) {
//                        new JaweWindow("Add World");
//                    }
//                }),

                new JaweColumns("Worlds Columns", 3, true),
                new JaweText("Worlds"),
                new JaweNextColumn(),
                new JaweText("Location Type"),
                new JaweNextColumn(),
                new JaweText("Location Path"),
                new JaweNextColumn(),
                new JaweSeparator()
        );
    }

    @Override
    protected void reset() {
        // TODO
    }
}
