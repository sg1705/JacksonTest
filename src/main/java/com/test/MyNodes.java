package com.test;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sagupta on 3/23/15.
 */
public class MyNodes {


    Node[] myNodes;
    
    Node[] otherNodes;

    public static void main(String[] args) throws Exception {
        MyNodes nn = new MyNodes();
        nn.createNodes();
        nn.toJson();
    }
    public void createNodes() {
        List<Node> nodes = new ArrayList();


        //create nodes
        Node a1 = new Node("A1");
        Node a2 = new Node("A2");
        Node a3 = new Node("A3");
        nodes.add(a1);
        nodes.add(a2);
        nodes.add(a3);
        a1.setParent(a3);
        a2.setParent(a1);
        a3.setParent(a1);

        this.myNodes = nodes.toArray(new Node[nodes.size()]);
        this.otherNodes = nodes.toArray(new Node[nodes.size()]);
    }

    public void toJson() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
        System.out.println(json);

        MyNodes mm = mapper.readValue(json, MyNodes.class);

        System.out.println(mm.myNodes[0] == mm.otherNodes[0]);
        System.out.println(mm.myNodes[0].parent);
    }
}
