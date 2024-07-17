package com.cos.photogramstart.domain.image;

import com.cos.photogramstart.domain.comment.Comment;
import com.cos.photogramstart.domain.likes.Likes;
import com.cos.photogramstart.domain.user.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String caption; // ex)나 오늘 너무 피곤하다~
    private String postImageUrl; // 사진을 전송받아서 그사진을 서버에 특정폴더에 저장- db에 그 저장된 경로를insert

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user; // 한명의 유저는 N개의 이미지 가능 1개의 이미지는 1명의 유저

    @OneToMany(mappedBy = "image")
    @JsonManagedReference
    private List<Likes> likes;

    @OneToMany(mappedBy = "image")
    @JsonManagedReference
    private List<Comment> comments;

    @Transient
    private boolean likeState;

    @Transient
    private int likeCount;

    private LocalDateTime createDate;

    @PrePersist
    private void createDate() {
        this.createDate = LocalDateTime.now();
    }
}
