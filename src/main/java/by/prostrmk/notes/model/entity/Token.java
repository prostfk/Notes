package by.prostrmk.notes.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Token {

    private String id;
    private String value;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "token")
    private User user;

    public Token(String value, User user) {
        this.value = value;
        this.user = user;
    }
}
