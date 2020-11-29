package server.Players;

import javax.persistence.*;
import javax.validation.constraints.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="players")
@EntityListeners(AuditingEntityListener.class)
public class Player {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long playerId;

    @NotNull(message = "Please enter teamId")
    private long teamId;

    @NotBlank
    private String teamName;

    @NotBlank
    private String position;

    private String collegeName;

    private String playerNumber;

    public Player() {
        super();
    }

    public Player(long teamId, String teamName, String Position) {
        super();
        this.teamId = teamId;
        this.teamName = teamName;
        this.position = Position;
    }

    public Player(String teamID, String position, String collegeName, String playerNumber) {
        super();
        this.teamId = Long.parseLong(teamID);
        this.position = position;
        this.collegeName = collegeName;
        this.playerNumber = playerNumber;
    }

    public Player(long teamId, String teamName, String Position, String collegeName, String playerNumber) {
        this(teamId, teamName, Position);
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

    public String toString() {
        return "playerId: " + this.playerId + ", teamId: " + this.teamId + ", teamName: " + this.teamName + ", position: " + this.position + ", collegeName: " + this.collegeName + ", playerNumber: " + this.playerNumber;
    }
}
