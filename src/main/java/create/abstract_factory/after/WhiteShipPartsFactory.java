package create.abstract_factory.after;

import create.abstract_factory.before.WhiteAnchor;
import create.abstract_factory.before.WhiteWheel;

public class WhiteShipPartsFactory implements ShipPartsFactory {

    @Override
    public Anchor createAnchor() {
        return new WhiteAnchor();
    }

    @Override
    public Wheel createWheel() {
        return new WhiteWheel();
    }
}
