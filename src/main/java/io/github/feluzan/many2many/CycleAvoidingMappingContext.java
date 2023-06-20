package io.github.feluzan.many2many;

import java.util.IdentityHashMap;
import java.util.Map;

public class CycleAvoidingMappingContext {
    private Map<Object, Object> knownInstances = new IdentityHashMap<>();

    public boolean isVisited(Object source) {
        return knownInstances.containsKey(source);
    }

    public void visit(Object source) {
        knownInstances.put(source, null);
    }
}