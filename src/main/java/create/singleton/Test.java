package create.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Test {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // 싱글톤은 new 생성자를 사용하지 않도록 설정해야함
        System.out.println(Settings.getInstanceDefault() == Settings.getInstanceDefault());
        System.out.println(Settings.getInstanceSynchronized() == Settings.getInstanceSynchronized());
        System.out.println(Settings.getInstanceEager() == Settings.getInstanceEager());
        System.out.println(Settings.getInstanceDoubleCheck() == Settings.getInstanceDoubleCheck());
        System.out.println(Settings.getInstanceInnerClass() == Settings.getInstanceInnerClass());
        System.out.println(EnumSettings.INSTANCE == EnumSettings.INSTANCE);

        //InnerClass 의 싱글톤 깨트리기
        //첫번째 리플렉션 사용하기
        Constructor<Settings> constructor = Settings.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        Settings settings = constructor.newInstance();
        System.out.println(settings == Settings.getInstanceInnerClass());

        //해결방안 enum은 리플렉션을 사용할수없음 단점은 미리 로딩시에 인스턴스가 만들어짐
        /*
        Constructor<EnumSettings> constructor2 = EnumSettings.class.getDeclaredConstructor();
        constructor2.setAccessible(true);
        EnumSettings settings2 = constructor2.newInstance();
        System.out.println(settings2 == constructor2.newInstance());
        */

        //두번째는 직렬화 역직렬화를 사용할때
        //대응 방안이 있음 readResolve 를 사용시 new 가아닌 getInstance 를 사용하도록 오버라이딩함


    }
}
