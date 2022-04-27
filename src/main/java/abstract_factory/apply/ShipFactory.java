package abstract_factory.apply;

import factorymethod.after.Ship;
import factorymethod.after.WhiteShip;
import org.springframework.beans.factory.FactoryBean;

public class ShipFactory implements FactoryBean<Ship> {
    @Override
    public Ship getObject() throws Exception {
        Ship ship = new WhiteShip();
        ship.setName("whiteShip");
        return ship;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }
}
