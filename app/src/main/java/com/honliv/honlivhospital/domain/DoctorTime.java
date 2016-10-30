package com.honliv.honlivhospital.domain;

import java.util.List;

/**
 * Created by Rodin on 2016/10/30.
 */
public class DoctorTime {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public List<String> getTime() {
        return time;
    }

    public void setTime(List<String> time) {
        this.time = time;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    private String skill;
    List<String> time;
    private String head;
}
