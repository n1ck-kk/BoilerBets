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

    public Player() {}

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
