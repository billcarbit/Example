package com.example.wangning.threelevellinkage;

import java.util.List;

public class Province implements BasePCA {

    private int areaVersion;        //版本号
    private String code;    //区域信息中城市对象的数组
    private String name;    //城市名称
    private List<City> city; //区域信息中城市对象的数组
    private boolean isChecked;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<City> getCity() {
        return city;
    }

    @Override
    public boolean isCity() {
        return false;
    }

    public void setCity(List<City> city) {
        this.city = city;
    }

    @Override
    public boolean isProvince() {
        return true;
    }

    @Override
    public boolean isArea() {
        return false;
    }

    public int getAreaVersion() {
        return areaVersion;
    }

    public void setAreaVersion(int areaVersion) {
        this.areaVersion = areaVersion;
    }

    public static class City implements BasePCA {

        private String code;
        private String name;
        private List<Area> area; //区
        private boolean isChecked;

        public boolean isChecked() {
            return isChecked;
        }

        public void setChecked(boolean checked) {
            isChecked = checked;
        }

        @Override
        public boolean isProvince() {
            return false;
        }

        @Override
        public boolean isArea() {
            return false;
        }

        public List<Area> getArea() {
            return area;
        }

        public void setArea(List<Area> area) {
            this.area = area;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public boolean isCity() {
            return true;
        }

        public static class Area implements BasePCA {
            private String code;
            private String name;
            private boolean isChecked;

            public boolean isChecked() {
                return isChecked;
            }

            public void setChecked(boolean checked) {
                isChecked = checked;
            }
            @Override
            public boolean isProvince() {
                return false;
            }

            @Override
            public boolean isArea() {
                return true;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            @Override
            public boolean isCity() {
                return false;
            }
        }
    }

}
