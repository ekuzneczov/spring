package org.example.spring.listener.entity;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EntityListener {

    @EventListener(condition = "#root.args[0].accessType.name() == 'DELETE'")
    public void acceptEntityRead(EntityEvent event) {
        System.out.println("Entity " + event);
    }
}
