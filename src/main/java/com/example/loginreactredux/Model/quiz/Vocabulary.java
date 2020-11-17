package com.example.loginreactredux.Model.quiz;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
public class Vocabulary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    private String loaitu;

    private String eng;
    private String vni;
    private String phienAm;
    private String dongnghia;
    @ManyToOne
    @JoinColumn(name = "topicvoc_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private TopicVoc topicVoc;
}
