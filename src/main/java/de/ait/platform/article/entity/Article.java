package de.ait.platform.article.entity;

import de.ait.platform.category.entity.Category;
import de.ait.platform.comments.entity.Comment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name="articles")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "photo")
    private String photo;
//
//    @ManyToMany(mappedBy = "articles")
//    private Set<Category> categories;
//
//    @OneToMany(mappedBy = "articles", cascade = CascadeType.ALL)
//    private Set<Comment> comments;
}
