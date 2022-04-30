package structural.composite.after;

import java.util.ArrayList;
import java.util.List;

public class Bag implements Component {

    private List<Component> components = new ArrayList<>();

    public void add(Component component) {
        components.add(component);
    }

    public List<Component> getComponent() {
        return components;
    }

    //공통기능
    @Override
    public int getPrice() {
        return components.stream().mapToInt(Component::getPrice).sum();
    }
}
