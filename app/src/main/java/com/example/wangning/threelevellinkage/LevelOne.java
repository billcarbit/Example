package com.example.wangning.threelevellinkage;

import java.util.List;

/**
 * Created by Administrator on 2017/5/11.
 */
public class LevelOne {
    private String level1;
    private List<Level2> level2List;

    class Level2 {
        private String level2;
        private List<Level3> level3List;

        public String getLevel2() {
            return level2;
        }

        public void setLevel2(String level2) {
            this.level2 = level2;
        }

        public List<Level3> getLevel3List() {
            return level3List;
        }

        public void setLevel3List(List<Level3> level3List) {
            this.level3List = level3List;
        }
    }

    class Level3 {
        private String level3;

        public String getLevel3() {
            return level3;
        }

        public void setLevel3(String level3) {
            this.level3 = level3;
        }
    }

}
