package com.example.wangning.threelevellinkage;

import java.util.List;

public class Province {

    private int areaVersion;        //版本号
    private List<ProvinceEntity> province;    //区域信息中省份对象的数组

    public int getAreaVersion() {
        return areaVersion;
    }

    public void setAreaVersion(int areaVersion) {
        this.areaVersion = areaVersion;
    }

    public List<ProvinceEntity> getProvince() {
        return province;
    }

    public void setProvince(List<ProvinceEntity> province) {
        this.province = province;
    }

    public static class ProvinceEntity implements BasePCA {

        private String code;    //区域信息中城市对象的数组
        private String name;    //城市名称
        private List<CityEntity> city; //区域信息中城市对象的数组

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

        public List<CityEntity> getCity() {
            return city;
        }

        @Override
        public boolean isCity() {
            return false;
        }

        public void setCity(List<CityEntity> city) {
            this.city = city;
        }

        public static class CityEntity implements BasePCA {

            private String code;
            private String name;
            private List<AreaEntity> area; //区

            public List<AreaEntity> getArea() {
                return area;
            }

            public void setArea(List<AreaEntity> area) {
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

            public static class AreaEntity implements BasePCA {
                private String code;
                private String name;

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

}
