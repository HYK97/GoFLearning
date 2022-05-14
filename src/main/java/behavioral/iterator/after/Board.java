package behavioral.iterator.after;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * ConcreateAggregate
 * RecentPostIterator를 생성해서 Client가 사용하도록함
 */
public class Board {

    List<Post> posts = new ArrayList<>();

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public void addPost(String content) {
        this.posts.add(new Post(content));
    }

    public Iterator<Post> getRecentPostIterator() {
        return new RecentPostIterator(this.posts);
    }
}
