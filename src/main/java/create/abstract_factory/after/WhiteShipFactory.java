package create.abstract_factory.after;


import create.factorymethod.after.Ship;
import create.factorymethod.after.ShipFactory;
import create.factorymethod.after.WhiteShip;

public class WhiteShipFactory implements ShipFactory {


    private ShipPartsFactory shipPartsFactory;

    public WhiteShipFactory(ShipPartsFactory shipPartsFactory) { //shipPartsFactory를 DI해줌
        this.shipPartsFactory = shipPartsFactory;
    }

    @Override
    public Ship createShip() {
        Ship ship = new WhiteShip();
        ship.setAnchor(shipPartsFactory.createAnchor());
        ship.setWheel(shipPartsFactory.createWheel());
        return ship;
    }
}
