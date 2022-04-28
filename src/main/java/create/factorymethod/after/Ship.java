package create.factorymethod.after;

import create.abstract_factory.after.Anchor;
import create.abstract_factory.after.Wheel;
import lombok.Data;

/**
 * Factory Method 의 구성요소인 Product
 * 여기서 Ship Class는 Product 는 Factory가 만들어주는 제품을 뜻함
 */

@Data
public class Ship {

    private String name;

    private String color;

    private String logo;

    private Wheel wheel;

    private Anchor anchor;


    @Override
    public String toString() {
        return "Ship{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", logo='" + logo + '\'' +
                '}';
    }
}
