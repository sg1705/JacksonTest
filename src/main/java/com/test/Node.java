package com.test;

import com.fasterxml.jackson.annotation.*;

/**
 * Created by sagupta on 3/23/15.
 */
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class Node {
    String name;

    @JsonManagedReference
    Node parent;

    public Node(String name) {
        this.name = name;
    }

    @JsonCreator
    public Node(@JsonProperty("name")String name, @JsonProperty("parent")Node parent) {
        this.name = name;
        this.parent = parent;
    }


    public void setParent(Node parent) {
        this.parent = parent;
    }
}
