package levelsystem.levelsystem;

public enum SkillTypes {
    HEALTH("Health"),
    RESISTANCE("Resistance"),
    STRENGTH("Strength"),
    AGILITY("Agility"),
    LUCK("Luck");

    private String skillName;

    SkillTypes(String skillName) {
        this.skillName = skillName;
    }

    public String getSkillName() {
        return this.skillName;
    }
}
