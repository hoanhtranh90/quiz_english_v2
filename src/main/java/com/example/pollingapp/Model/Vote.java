package com.example.pollingapp.Model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity

public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    private String title;

    @OneToMany( mappedBy = "vote",cascade = CascadeType.ALL)
    private Set<VoteChoice> voteChoices = new HashSet<>();
    // áp dụng quy tắc S trong solid có thẻ tạo 1 class DTO
    // => không cần dùng đến jsonignore


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<VoteChoice> getVoteChoices() {
        return voteChoices;
    }

    //de tranh field vote trong voteChoice null
    public void setVoteChoices(Set<VoteChoice> voteChoices) {
        this.voteChoices = voteChoices;
        for(VoteChoice v:voteChoices)
        {
            v.setVote(this);
        }
    }
}
