package structural.flyweight.after;


import java.util.HashMap;
import java.util.Map;

/**
 * flyWeight Factory 객체
 */
public class FontFactory {
    private Map<String, Font> cache = new HashMap<>();


    /**
     * cache.containsKey(font) 캐쉬된 객차가 있으면 그객체를 리턴하고
     * 아니면 새로운 객체를 생성한후 리턴
     *
     * @param font
     * @return
     */
    public Font getFont(String font) {
        if (cache.containsKey(font)) {
            return cache.get(font);
        } else {
            String[] split = font.split(":");
            Font newFont = new Font(split[0], Integer.parseInt(split[1]));
            cache.put(font, newFont);
            return newFont;
        }

    }

}
