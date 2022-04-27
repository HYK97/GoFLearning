package abstract_factory.after;

public class WhiteShipProPartsFactory implements ShipPartsFactory {

    @Override
    public Anchor createAnchor() {
        return new WhiteProAnchor();
    }

    @Override
    public Wheel createWheel() {
        return new WhiteProWheel();
    }
}
