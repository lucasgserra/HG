package rainprojects.hg.grupos;

public enum GruposEnum {

    DEFAULT("member", "§7", 0),
    VIP("vip", "§a", 1),
    MVP("mvp", "§b", 2),
    YOUTUBER("youtuber", "§c", 3),
    TRIAL("trial-moderator", "§d", 4),
    MODERATOR("moderator", "§5", 5),
    ADMIN("administrator", "§c", 6),
    ;

    private final String name, prefix;
    private final int weight;

    GruposEnum(String name, String prefix, int weight) {
        this.name = name;
        this.prefix = prefix;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public String getPrefix() {
        return prefix;
    }

    public int getWeight() {
        return weight;
    }
}
