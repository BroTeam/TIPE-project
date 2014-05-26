package com.broteam.tipe.model;

import com.broteam.tipe.model.elements.AccessPoint;
import com.broteam.tipe.model.elements.Element;
import com.broteam.tipe.model.elements.Obstacle;

@SuppressWarnings("unused")
public interface ModelListener {

    default void onElementAdded(Element e) {};
    default void onObstacleAdded(Obstacle o) {};
    default void onAccessPointAdded(AccessPoint ap) {};
    
    default void onElementRemoved(Element e) {};
    default void onObstacleRemoved(Obstacle o) {};
    default void onAccessPointRemoved(AccessPoint ap) {};
    
    default void onCleared() {};    
}
