package behavioral.iterator.after;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * ConcreateIterator
 */
public class RecentPostIterator implements Iterator<Post> {


    /**
     * 자바에서 지원해주는
     * Iterator 인터페이스를 이용해서 필요한 메서드를 구현함
     */

    private Iterator<Post> internalIterator;


    /**
     * posts를 Sorting 해서 iterator로 리턴해줌
     *
     * @param posts
     */
    public RecentPostIterator(List<Post> posts) {
        //정렬된 iterator;
        Collections.sort(posts, (p1, p2) -> p1.getCreatedDateTime().compareTo(p1.getCreatedDateTime()));
        this.internalIterator = posts.iterator();
    }

    /**
     * @return 다음 객체 여부
     */
    @Override
    public boolean hasNext() {
        return this.internalIterator.hasNext();
    }


    /**
     * @return 다음 Post객체 리턴
     */
    @Override
    public Post next() {
        return this.internalIterator.next();
    }

}
