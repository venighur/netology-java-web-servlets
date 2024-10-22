package ru.netology.repository;

import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

// Stub
public class PostRepository {
    ConcurrentHashMap<Long, String> posts = new ConcurrentHashMap<>();
    private long counter = 0;

    public List<Post> all() {
        List<Post> posts = new ArrayList<>();

        for (long key: this.posts.keySet()) {
            posts.add(new Post(key, this.posts.get(key)));
        }

        return posts;
    }

    public Optional<Post> getById(long id) {
        if (!posts.containsKey(id)) {
            throw new NotFoundException("Пост не найден");
        }
        return Optional.of(new Post(id, posts.get(id)));
    }

    public Post save(Post post) {
        counter++;
        posts.put(counter, post.getContent());
        return post;
    }

    public void removeById(long id) {
        posts.remove(id);
    }
}
