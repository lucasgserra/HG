package rainprojects.hg.grupos;

import rainprojects.hg.HG;

import java.util.HashMap;
import java.util.Map;

public class GrupoManager {

    private static Map<String, GruposEnum> grupo = new HashMap<>();

    public static Map<String, GruposEnum> getGrupo() {
        return grupo;
    }

    public static void load() {
        HG.getInstance().getConfig().getConfigurationSection("").getKeys(false).forEach(names -> {
            String grupoName = HG.getInstance().getConfig().getString(names);
            grupo.put(names, GruposEnum.valueOf(grupoName.toUpperCase()));
        });
    }
    public static void save() {
        grupo.forEach((k,v)->{
            HG.getInstance().getConfig().set(k, v.toString());
            HG.getInstance().saveConfig();
        });
    }
    private static boolean exists(String name) {
        return grupo.containsKey(name);
    }
    public static void createPlayer(String name) {
        if (exists(name)) return;
        grupo.put(name, GruposEnum.DEFAULT);
    }
    public static GruposEnum group(String name) {
        return grupo.get(name);
    }
}
