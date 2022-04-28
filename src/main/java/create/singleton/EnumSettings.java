package create.singleton;

public enum EnumSettings {
    /**
     * 이렇게 enum 클래스로 객체를 생성하면 안전하게 싱글톤 객체를 만들어 사용할수 있음.
     * 단점은 상속 받을 수 없음
     */
    INSTANCE;
}
