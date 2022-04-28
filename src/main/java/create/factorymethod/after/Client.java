package create.factorymethod.after;

public class Client {

    /**
     * 팩토리 메서드 패턴의
     * 장점 : 변경에 닫혀있고 확장에 열려있는 ocp를 잘따를수있다. 즉 기본코드를 코치지않고 새로운 기능들을 추가 할 수있다
     * 단점 : 클래스가 많이 늘어 날 수 있다.
     */

    public static void main(String[] args) {
        Client client = new Client();
        client.print(new WhiteShipFactory(), "whiteship", "kkk@naver.com");
        client.print(new BlackShipFactory(), "blackship", "kksssk@naver.com");
    }

    private void print(ShipFactory shipFactory, String name, String email) {
        System.out.println(shipFactory.orderShip(name, email));
    }
}
