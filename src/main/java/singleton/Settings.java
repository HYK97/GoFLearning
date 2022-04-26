package singleton;

public class Settings {

    /**
     * 싱글톤객체는 private 생성자를 이용해야함
     * 결국 외부에서 생성자를 접근하는게아닌 내부에서 생성된 객체를 접근 할 수 있는 방법을 제시해야된다.
     */


    private Settings() {
    }
    //이러한 방법은 멀티 스레드 환경에 적합하지않은 싱글톤 객체 생성방법 동시성 문제가 있음.


    //Default
    private static Settings instance1;

    public static Settings getInstanceDefault() {
        if (instance1 == null) {
            instance1 = new Settings();
        }
        return instance1;
    }

    /**
     * 해결방법
     * 1.sychronized - 가장 간단하지만 약간의 성능 저하가있음
     * 2.이른 초기화 방법 - 생성 객체를 미리 만들어놓고 쓰지않으면 앱로딩시 많은 자원을 소모함
     * 3.double checked locking - 동기화 작업을 인스턴스가 널인 경우에만 확인하도록 해서 리소스 소모를 줄이는 방법
     * 4.static inner Class 사용하기 - 권장하는 방법
     * 5.enumClass 사용하기 - 권장하는 방법
     */


    //synchronized
    private static Settings instance2;

    public static synchronized Settings getInstanceSynchronized() {
        if (instance2 == null) {
            instance2 = new Settings();
        }
        return instance2;
    }
    //


    //이른 초기화
    private static final Settings INSTANCE = new Settings();

    public static Settings getInstanceEager() {
        return INSTANCE;
    }


    //double checked locking
    private static volatile Settings instance3;

    public static synchronized Settings getInstanceDoubleCheck() {
        if (instance3 == null) {
            synchronized (Settings.class) {
                if (instance3 == null) {
                    instance3 = new Settings();
                }
            }
        }
        return instance3;
    }


    // static inner Class 방법
    private static class SettingsHolder {
        private static final Settings INSTANCE = new Settings();
    }

    public static Settings getInstanceInnerClass() {
        return SettingsHolder.INSTANCE;
    }

    //역직렬화 대응방안
    protected  Object readResolve() {
        return getInstanceInnerClass();
    }



}
