package truss;

import lombok.Getter;

public class JointsPair {
@Getter
    private final Joint first;
@Getter
    private final Joint second;

    public JointsPair(Joint first, Joint second) {
        this.first =first;
        this.second = second;
    }

}
