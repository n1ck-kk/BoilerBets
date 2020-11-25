package server.Players;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "players")
public class Player {
    @Id
    @GeneratedValue
    @Column (name="playerId")
    private long playerId;

    @NotBlank
    @Column (name="teamId")
    private long teamId;

    @NotBlank
    @Column (name="teamName")
    private String teamName;

    @NotBlank
    @Column (name="position")
    private String position;

    @Column (name="collegeName")
    private String collegeName;

    @Column (name="playerNumber")
    private String playerNumber;

    protected Player() {}

    public Player(long playerId, long teamId, String teamName, String Position) {
        super();
        this.playerId = playerId;
        this.teamId = teamId;
        this.teamName = teamName;
        this.position = Position;
    }

    public Player(long playerId, long teamId, String teamName, String Position, String collegeName, String playerNumber) {
        this(playerId, teamId, teamName, Position);
        this.collegeName = collegeName;
        this.playerNumber = playerNumber;
    }

    public long getId() {
        return this.playerId;
    }

    public long getTeamId() {
        return this.teamId;
    }

    public String getTeamName() {
        return this.teamName;
    }

    public String getPosition() {
        return this.position;
    }

    public void setTeam(long teamId, String teamName) {
        this.teamId = teamId;
        this.teamName = teamName;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
