package create.builder.apply;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class SpringExample {

    public static void main(String[] args) {
        UriComponents components = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("www.naver.com")
                .path("news")
                .build().encode();
        System.out.println("create.builder = " + components);


    }
}
