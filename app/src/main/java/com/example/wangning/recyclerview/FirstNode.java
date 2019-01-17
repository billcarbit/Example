package com.example.wangning.recyclerview;

import java.util.List;

public class FirstNode {
    private String name;
    private boolean isSelected;
    private List<SecondNode> secondList;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public static class SecondNode {
        private String name;
        private boolean isSelected;

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SecondNode> getSecondList() {
        return secondList;
    }

    public void setSecondList(List<SecondNode> secondList) {
        this.secondList = secondList;
    }
}
