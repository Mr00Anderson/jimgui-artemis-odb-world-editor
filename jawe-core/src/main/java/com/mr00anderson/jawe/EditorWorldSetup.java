package com.mr00anderson.jawe;

import com.artemis.Archetype;
import com.artemis.ArchetypeBuilder;
import com.artemis.ComponentMapper;
import com.artemis.World;
import com.artemis.managers.GroupManager;
import com.mr00anderson.jawe.components.JaweRenderComponent;
import com.mr00anderson.jawe.drawables.JaweDrawable;
import com.mr00anderson.jawe.drawables.JaweWindow;
import com.mr00anderson.jawe.utils.JaweUtils;

public class EditorWorldSetup {




    public static void setupEditorBaseEntities(final World world) {
        buildMainMenu();

        JaweDrawable debugWindow = JaweWindow.JaweWindowBuilder
                .aJaweWindow()
                .label("Debug")
                .windowContents(new DebugWindow())
                .build();

        JaweDrawable mainMenuBar;


        JaweDrawable[] jaweDefaultBuildEntities = {
                debugWindow,


//                new MainMenuBarComponent(),// TODO dont forget debug window enable disable
                // Debug Window

                // Project browser ? This will be something eventually
                // World edit window - Selectable Worlds (project parents)
                // File browser (project parent), components, entities, prefabs can be loaded into a world or (STAGING Area)



        };



        Archetype archetype = new ArchetypeBuilder().add(JaweRenderComponent.class).build(world);
        ComponentMapper<JaweRenderComponent> mapper = world.getMapper(JaweRenderComponent.class);
        GroupManager system = world.getSystem(GroupManager.class);
        for (int i = 0; i < jaweDefaultBuildEntities.length; i++) {
            int entityId = world.create(archetype);
            JaweRenderComponent component = mapper.create(entityId);
            system.add(entityId, "JaweDrawable");
            component.active = JaweUtils.createBool(true);
            component.jaweDrawable = jaweDefaultBuildEntities[i];
        }

    }

    private static void buildMainMenu() {

    }

}
