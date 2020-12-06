package server.PlayerStats;

public class PlayerStats {
    
    private long playerId;
    private long avgP;
    private long avgAST;
    private long avgBLK;
    private long avgSTL;
    private long avgTO;
    private long avgMin;
    private long avgFG;
    private long avgFG3;
    private long avgFT;

    public PlayerStats(long playerId, String avgP, String avgAST, String avgBLK, String avgSTL, String avgTO, String avgMin, String avgFG, String avgFG3, String avgFT) {
        this.playerId = playerId;
        this.avgP = Long.parseLong(avgP);
        this.avgAST = Long.parseLong(avgAST);
        this.avgBLK = Long.parseLong(avgBLK);
        this.avgSTL = Long.parseLong(avgSTL);
        this.avgTO = Long.parseLong(avgTO);
        this.avgMin = Long.parseLong(avgMin);
        this.avgFG = Long.parseLong(avgFG);
        this.avgFG3 = Long.parseLong(avgFG3);
        this.avgFT = Long.parseLong(avgFT);
    }

    public void setID(long id) {
        this.playerId = id;
    }

    public long getAvgP() {
        return avgP;
    }

    public long getAvgAST() {
        return avgAST;
    }

    public long getAvgBLK() {
        return avgBLK;
    }

    public long getAvgSTL() {
        return avgSTL;
    }

    public long getAvgTO() {
        return avgTO;
    }

    public long getAvgMin() {
        return avgMin;
    }

    public long getAvgFT() {
        return avgFT;
    }

    public long getAvgFG() {
        return avgFG;
    }

    public long getAvgFG3() {
        return avgFG3;
    }
}
