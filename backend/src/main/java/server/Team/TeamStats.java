package server.Team;

public class TeamStats{

	public int teamId;
    public String teamName;
	public int wins;
    public int losses;
    public int winPct;
    public int avgP;
    public int avgFGP;
    public int avg3PP;
    public int avgFTP;
    public int avgAST;
    public int avgTO;
    public int avgBLK;




	public TeamStats(){
		this.teamId = 0;
        this.teamName = "";
	    this.wins = 0;
        this.losses = 0;
        this.winPct = 0;
        this.avgP = 0;
        this.avgFGP = 0;
        this.avg3PP = 0;
        this.avgFTP = 0;
        this.avgAST = 0;
        this.avgTO = 0;
        this.avgBLK = 0;
	
	}


	public TeamStats(int teamId,
	 int wins,
     int losses,
     int winPct,
     int avgP,
     int avgFGP,
     int avg3PP,
     int avgFTP,
     int avgAST,
     int avgTO,
     int avgBLK)
	{
		this.teamId = teamId;
	    this.wins = wins;
        this.losses = losses;
        this.winPct = winPct;
        this.avgP = avgP;
        this.avgFGP = avgFGP;
        this.avg3PP = avg3PP;
        this.avgFTP = avgFTP;
        this.avgAST = avgAST;
        this.avgTO = avgTO;
        this.avgBLK = avgBLK;
	}


}