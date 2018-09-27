package com.example.wangning.coordinator;

import java.util.List;

/**
 * Created by admin on 2018/9/8.
 */
public class Comment {

    private String content;
    private List<Replay> replayList;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Replay> getReplayList() {
        return replayList;
    }

    public void setReplayList(List<Replay> replayList) {
        this.replayList = replayList;
    }

    public static class Replay {
        private String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
