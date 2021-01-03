package model.delegation;

import model.delegation.Delegation;

public class DelegationFactory {

    public static Delegation getDelegation(int name) {
        switch(name) {
            case 1:
                return new Barcelona(1, DelegationCentral.BARCELONA.toString());
            case 2:
                return new Lleida(2, DelegationCentral.LLEIDA.toString());
            case 3:
                return new Tarragona(3, DelegationCentral.TARRAGONA.toString());
            case 4:
                return new Girona(4, DelegationCentral.GIRONA.toString());
            default:
                return null;
        }
    }
}
