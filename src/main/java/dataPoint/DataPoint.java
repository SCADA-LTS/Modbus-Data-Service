package dataPoint;

public class DataPoint {
    String xid;
    int type;
    int offset;
    int bit;
    String name;

    public DataPoint(String xid, int type, int offset, int bit, String name){
        this.xid = xid;
        this.type = type;
        this.offset = offset;
        this.bit = bit;
        this.name = name;
    }

    public String getXid() {
        return xid;
    }

    public void setXid(String xid) {
        this.xid = xid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getBit() {
        return bit;
    }

    public void setBit(int bit) {
        this.bit = bit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
