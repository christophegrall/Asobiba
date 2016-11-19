package net.orekyuu.repository.entity;

import org.seasar.doma.Domain;

@Domain(valueType = String.class, factoryMethod = "valueOf")
public enum RoomName {

    E204,
    E302,
    E303,
    E304,
    E306,
    E308,
    E401,
    E402,
    E403,
    E404,
    E405,
    E406,
    E407,
    E408,
    E409,
    E410,
    E501,
    E502,
    E503,
    E504,
    E505,
    E506,
    E507,
    E508,
    E509,
    E510,
    E511,
    E512;

    public String getValue() {
        return name();
    }
}
