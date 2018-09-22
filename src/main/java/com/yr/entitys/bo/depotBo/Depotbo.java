package com.yr.entitys.bo.depotBo;

import com.yr.entitys.depot.Depot;

/**
 * 仓库扩展类
 */
public class Depotbo {
    private Depot depot;
    private String code;
    private String name;
    private String addr;

    public Depot getDepot() {
        return depot;
    }

    public void setDepot(Depot depot) {
        this.depot = depot;
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

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    @Override
    public String toString() {
        return "Depotbo{" +
                "depot=" + depot +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", addr='" + addr + '\'' +
                '}';
    }
}
